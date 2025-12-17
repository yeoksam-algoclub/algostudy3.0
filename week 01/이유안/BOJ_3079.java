import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class BOJ3079 {
    public static long n,m;
    public static List<Long> time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n개,m명, n줄동안 심사시간 t1~t2
        String[] brs = br.readLine().split(" ");
        n = Long.parseLong(brs[0]);
        m = Long.parseLong(brs[1]);

        time = new ArrayList<>();
        long maxTk=0,nowTk=0;
        for (int i=0; i<n; i++){
            nowTk = Long.parseLong(br.readLine());
            if (nowTk>maxTk) maxTk = nowTk;
            time.add(nowTk);
        }
        long minAns= Long.MAX_VALUE; //
        // 이분탐색: 중간시간 구하기
        long minT = 0;
        long maxT = m*maxTk;// 최대시간은 모든사람이 한군데

        while (minT <= maxT){
            long midT = (minT+maxT)/2; // 이분
            if (pass(midT)){ // 모두 통과면 시간 줄여야함
                // 현재시간 저장
                minAns = Math.min(minAns,midT);
                maxT = midT-1;
            } else { // 못통과하면 시간늘여야함
                minT = midT+1;
            }
        }

        System.out.println(minAns);
    }
    public static boolean pass(long nowTime){
        long passP=0;
        // 현재 시간동안 n명이 모두 통과할수 있는지 체크
        for (long t:time){ // nowTime 나누기 t 정수
            passP+=(nowTime/t);
            if(passP>=m) {
                return true;
            }
        }
        return false;
    }
}