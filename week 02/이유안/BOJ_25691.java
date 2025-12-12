import java.io.*;
import java.sql.Array;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int n,k,total,maxTotal;
    public static int[] apple ;
    public static ArrayList<Integer>[] map;
    public static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /*
     n개 노드, k번 이동
     */
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt((st.nextToken()));
    // n개의 노드 > 자식있음
    map = new ArrayList[n];
    for (int i=0; i<n; i++){
        map[i] = new ArrayList<>();
    }
    //n-1 번동안 p와 c 간선 연결
    for (int i=0; i<(n-1); i++){
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt((st.nextToken()));
        map[p].add(c);
        }
    String[] stringApp = br.readLine().split(" ");
    //System.out.println(Arrays.toString(stringApp));
    apple = new int[n];
    for (int i=0; i<n; i++){
        apple[i] = Integer.parseInt(stringApp[i]);
    }

    int[][] dp = new int[n][k+1]; // n개노드의 k 번이동
     // 0노드에서 시작해서 k 번 이동
    for (int i=0; i<n; i++){
        Arrays.fill(dp[i],-1);
        dp[i][1] = apple[i];
    }
    dfs(0,dp);
    int ans = 0;
    for (int i=0; i<(k+1); i++){
        if(dp[0][i]>ans){
            ans = dp[0][i];
        }
    }
        System.out.println(ans);
    }
    public static void dfs(int now, int[][] dp) {
        for (int nxt : map[now]) {
            dfs(nxt,dp); // 자식 노드의 서브트리부터 먼저 구하기

            // 계속 내려와서 리프노드까지 내려왔을떄 가정
            int[] temp = Arrays.copyOf(dp[now],k+1); // 직전 배열 저장
            // 이중 포문으로 서브 트리 검색
            for (int p=1; p<=k; p++){
                if (temp[p] == -1)continue;// 안차있으면 아예고려x
                for (int q=1; q<=k; q++){
                    if (dp[nxt][q] == -1) continue; // 마찬가지로 안차있으면 고려x

                    // 기준이 k개니까 k개 이하일때만 체크함
                    if ((p+q) <=k){
                        int newValue =  temp[p] + dp[nxt][q];
                        if (dp[now][p+q] < newValue){
                            dp[now][p+q] = newValue;
                        }
                    }
                }
            }
        }

    }

}