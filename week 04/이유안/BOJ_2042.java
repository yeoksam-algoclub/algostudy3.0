import java.io.*;
import java.util.Arrays;

class Main {
    public static long n,m,k;
    public static long nowsum;
    public static String[] strings;
    public static long[] values;
    public static long[] tree;
    public static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
/*
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000)
 M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다.
 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데,
   */
        strings = br.readLine().split(" ");
        n = Long.parseLong(strings[0]);
        m = Long.parseLong(strings[1]);
        k = Long.parseLong(strings[2]);
        // n개의 수 가 주어짐
        values = new long[(int)n+1]; tree = new long[(int)n*4];
        // 원래값 & 구간합 값 , *2 할거니까 1부터 넣기
        for (int i=1; i<(n+1); i++){
            values[i] = Long.parseLong(br.readLine());
        }
        // 트리 채우기 함수
        fullTreeSum(1,n,1);//System.out.prlongln(Arrays.toString(tree));
        //N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c
        long a,b,c;
        for (long j=0; j<(m+k); j++){
            strings= br.readLine().split(" ");
            a = Long.parseLong(strings[0]);
            b = Long.parseLong(strings[1]);
            c = Long.parseLong(strings[2]);
            if (a==1){ // b번째 c로
                long temp = values[(int)b];
                values[(int)b] = c;
                change(1,n,1,b,c-temp); // 인덱스,바꿀값
            } else { // b부터 c까지 합
                nowsum=0;
                sum(1,n,1,b,c);
                sb.append(nowsum).append("\n");
            }
        }
        System.out.println(sb.toString());

    }
    public static long fullTreeSum(long s, long e, long now){ // 구간 s-e의 합이 tree[now]에 저장
        if (s==e){
            tree[(int)now] = values[(int)s];
            return values[(int)s];
        } else {
            long mid = (s+e)/2;
            // 현재노드는 아래 2*n 과 2*n+1희 합,
            return tree[(int)now] = fullTreeSum(s,mid,2* now) + fullTreeSum(mid+1,e,2* now +1);
        }
    }
    public static void change(long s, long e, long now, long idx,long v){ // idx 번째를 v로 바꿈
        if ( s<= idx && idx<=e){
        // idx 가 들어있는 구간은 (v-temp) 씩 더해주기
            tree[(int)now] +=(v);
        } else if ( idx<s || idx>e ) return;
        if (s>=e) return;
        long mid = (s+e)/2;
        change(s,mid,now*2,idx,v);
        change(mid+1,e,now*2+1,idx,v);

    }
    public static void sum(long s,long e, long now, long l, long r){
        if (s>=l && e<=r){
            nowsum+=tree[(int)now];
            return;
        } else if (r < s || e < l) return;
        long mid = (s+e)/2;
        sum(s,mid,now*2,l,r);
        sum(mid+1,e,now*2+1,l,r);
    }
}