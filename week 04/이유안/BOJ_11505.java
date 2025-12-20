import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BOJ2042 {
    public static int n, m, k;
    public static long num = 1000000007;
    public static long[] values;
    public static long[] tree;
    public static StringBuilder sb;
    public static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
/*
1,000,000,007로 나누기
N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000)
   */
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        //
        values = new long[n + 1];
        tree = new long[n * 4];
        // 원래값 & 구간합 값 , *2 할거니까 1부터 넣기
        for (int i = 1; i < (n + 1); i++) {
            values[i] = Long.parseLong(br.readLine());
        }
        // 트리 채우기 함수
        fullTreeMul(1, n, 1);
        //System.out.println(Arrays.toString(tree));
        //N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c
        int a, b, c;
        for (int j = 0; j < (m + k); j++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a == 1) { // b번째 c로
                values[b] = c;
                change(1,n,1,b, c);
            } else { // b부터 c까지 합
                long nowMul = mul(1, n, 1, b, c); // 범위,시작노드,구간좌,우
                sb.append(nowMul).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static long fullTreeMul(int s, int e, int now){ // 구간 s-e의 합이 tree[now]에 저장
        if (s==e){
            return tree[now] = values[s];
        } else {
            int mid = (s+e)/2;
            // 현재노드는 아래 2*n 과 2*n+1희 합,
            return tree[now] = fullTreeMul(s,mid,2* now) * fullTreeMul(mid+1,e,2* now +1) % num;
        }
    }

    // 현재 구간에 idx 가 있는 경우에 tree[now]를 업데이트
    public static long change(int s, int e, int now, int idx, long c) {
        if (idx < s || idx > e) return tree[now];// 구간 밖이면 변화x
        if (s == e) { //구간에 idx가 있고 리프노드  = 즉 idx 본인노드임
            return tree[now] = (c % num);
        }
        int mid = (s + e) / 2;
        return tree[now] = (change(s, mid, 2 * now, idx, c) * change(mid + 1, e, 2 * now + 1, idx, c)) % num;
    }
    public static long mul(int s, int e, int now, int l, int r){ // 범위,시작노드,구간좌,우
        // l 부터 r 구하기 : s-e 가 l   s   e   r    1    12-> 1 6 /7 12    , 3--8 (l,r)
        if (e<l || r<s){
            return 1;
        }
        else if (l<=s && e<=r){
            return tree[now];
        }
        int mid = (s+e)/2;
        return (mul(s,mid,2*now,l,r) * mul(mid+1,e,2*now+1,l,r))%num;
    }
}
