import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static int t,n;
    public static List<String> words;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /*
        한 번호가 다른 번호의 접두어
        -> 내 번호 치는중에 다른 번호가 완성되면 안됨
         */
        t = Integer.parseInt(br.readLine());
        for ( int i=0; i<t; i++) {
           n = Integer.parseInt(br.readLine());
            words = new ArrayList<>();
            for (int j=0; j<n; j++){
                words.add(br.readLine());
            }
            Collections.sort(words);

            if (test(words)){
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }
        System.out.println(sb.toString());

    }
    public static boolean test(List<String> words) throws Exception {
        // 사전순으로 정렬한다음에 앞숫자의 접두어가 뒤에 있는지 비교
        String fst, sec;
        for (int i = 0; i < words.size() - 1; i++) {
            fst = words.get(i);
            sec = words.get(i + 1);
            if (sec.startsWith(fst)) return false;
        }
        return true;
    }
}