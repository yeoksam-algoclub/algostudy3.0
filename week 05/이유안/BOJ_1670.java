 import java.util.*;
 import java.io.*;

 class Main {
     public static int n;
     public static long res;
     public static long num = 987654321;
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         n = Integer.parseInt(br.readLine()); // 10,000보다 작거나 같은 짝수
         long[] dp = new long[n+1];
          /* 1/ 5
         i 명이 악수하는 수: 좌 j 명, 우 i=j+2 명
         dp[i] = dp[n-j-2] * dp[j]
          */
         // 점화식이면 앞부터 넣으면 됨 -> 앞에 그냥 1로채워주기
         dp[0] = 1; dp[1] = 1; dp[2] = 1;
//         dp[4] = dp[0] * dp[2] + dp[2] * dp[0]; //2
         for (int i=4; i<=n; i+=(2)){ // i 부터 n까지 채우기
            for (int j=0; j<=(i-2); j+=(2)){ // i일떄 j * (i-j-2)
                long nxt = dp[j] * dp[i-j-2];
                dp[i]+=(nxt);
                dp[i] %= num;
            }
         }
         System.out.println(dp[n]);


     }

 }