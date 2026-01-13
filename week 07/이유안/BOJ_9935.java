
import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String target = br.readLine();
        String word = br.readLine();
        int woLen = word.length();

        boolean flag;
        // target 돌면서 뒷자리기준으로 확인
        for (int i=0; i<target.length(); i++){
            sb.append(target.charAt(i));
            // sb 길이 확인
            int sbLen = sb.length();
            if (sbLen < woLen) continue;
            // 같거나 클때 뒤부터 순회하면서 뒷자리부터 확인
            flag = true;
            for ( int j=0; j<woLen; j++){
                if (sb.charAt(sbLen-j-1) != word.charAt(woLen-j-1)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                // 다같은 경우
                sb.delete(sbLen-woLen,sbLen);
            }
        }
        if (sb.length() == 0){
            System.out.println("FRULA");
        } else {

            System.out.println(sb.toString());
        }
    }

}