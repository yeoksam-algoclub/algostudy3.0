import javax.lang.model.type.ArrayType;
import java.sql.Array;
import java.util.*;
import java.io.*;

class Main{
    public static int n,m,res;
    public static int[] visit,tasks;
    public static List<Integer>[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        // n개 줄동안 i번직원 일개수, 일 번호
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            // i번째 직원이 할수있는일 리스트
            arr[i+1] = new ArrayList<>();
            for (int j=0; j<num; j++){
                int wnum = Integer.parseInt(st.nextToken());
                arr[i+1].add(wnum);
            }
        }
        tasks = new int[m+1]; // 업무 중인 사람 저장
        visit = new int[n+1]; // 양보 해준 사람은 매번 새로 체크
        int visitCnt = 0;
        for (int i=0; i<n; i++){
            visitCnt++; // 매회차 다르게 카운트
            if (dfs(i+1,visitCnt)){
                res++;
            }
        }
        System.out.println(res);
    }
    // 이분매칭 : 두 집단을 효율적으로 매칭 (이경우 직원 - 일)
    public static boolean dfs(int emp, int visitCnt) {
        // 이미 불가능한 경로면 중간에 리턴
        if (visit[emp] == visitCnt) return false;
        visit[emp] = visitCnt; // 자기 포함 루프 방지 / 회차 정보 저장
        // 할당하기
        for (int t : arr[emp]) {
            if (tasks[t] == 0 || dfs(tasks[t],visitCnt)) {
                tasks[t] = emp; // t 업무는 emp 가 하고있음
                return true; //  가능한 업무 있으면 즉시 배정
            }
        }
        return false;// 배정 못받음
    }
}
