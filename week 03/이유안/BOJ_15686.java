import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int n,m,chickCnt,hmsCnt,minDist;
    public static int[][] city;
    public static List<int[]> chicks, hms, cords;
    public static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
        n,m / n*n
        도시정보: 0,1,2 - 빈칸/집/치킨집
        랜덤치킨집 m개일때 치킨거리 최솟값
         */
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 도시
        city = new int[n][n];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int j =0;
            while (st.hasMoreTokens()){
                city[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }
        //System.out.println(Arrays.toString(city));
        // 도시정보중에 2인 치킨집 좌표 뽑아내기
        chicks = new ArrayList<>();
        hms = new ArrayList<>();

        chickCnt = 0;
        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if (city[i][j] == 2) {
                    chicks.add(new int[]{i, j});
                    chickCnt++;
                } else if (city[i][j] == 1){
                    hms.add(new int[]{i,j});
                    hmsCnt++;
                }
            }
        }
        // 치킨집좌표 중에 m개 랜덤으로 뽑는 함수
        int[] check = new int[chickCnt+1];
        cords = new ArrayList<int[]>(); // check 여기다 모으기
        dfs(check,0,0); // 체크, 현재인덱스,고른갯수
        // m개 좌표에 대해서 각 집에 최소의 치킨거리 구하기
        minDist = 100000000;
        for (int[] cord:cords){
            findMin(city,cord);
        }
        // 치킨거리들 더한거 저장
        System.out.println(minDist);
    }
    public static void dfs(int[] check,int idx,int ck){
        if(ck == m){
            int[] newcheck = Arrays.copyOf(check,chickCnt);
            cords.add(newcheck); // 저장
            return;
        }
        // 남은개수 한번 더체크해서 백트래킹
        if ((chickCnt-idx+ck)<m){
            return;
        }
        if (idx >= chickCnt) {
            return;
        }
        // 체크안하고 넘김
        dfs(check,idx+1,ck);
        check[idx] = 1;
        dfs(check,idx+1,ck+1);
        check[idx] = 0;
    }
    public static void findMin(int[][] city,int[] cord){ //cord: 랜덤 치킨집check[]
        int minsum = 0; // 최단거리 총합
        for (int i=0; i<hmsCnt; i++){
            int[] hm = hms.get(i); // 집좌표
            // 현재 집에서 가장 가까운 치킨집까지의 거리
            int mindist = 10000;
            for (int j=0; j<chickCnt; j++){
                if (cord[j] ==0) continue;
                int[] chick =chicks.get(j);
                int dist = Math.abs(hm[0]-chick[0]) + Math.abs(hm[1]-chick[1]);
                if (dist < mindist){
                    mindist = dist;
                }
            }
            minsum+=mindist;
        }
        //System.out.println(minsum);
        if (minsum<minDist){
            minDist = minsum;
        }
    }

}