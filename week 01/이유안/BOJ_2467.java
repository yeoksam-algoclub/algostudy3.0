import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터 설정을 위한 변수
        int left = 0;
        int right = n - 1;

        long minAbsSum = Long.MAX_VALUE;
        int res1 = 0;
        int res2 = 0;

        while (left < right) {
            long sum = (long)values[left] + values[right];

            if (Math.abs(sum) < minAbsSum) {
                minAbsSum = Math.abs(sum);
                res1 = values[left];
                res2 = values[right];
            }
            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                System.out.println(res1 + " " + res2);
                return;
            }
        }

        System.out.println(res1 + " " + res2);
    }
}