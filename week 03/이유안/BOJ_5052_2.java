import java.util.*;
import java.io.*;

class treeNode {
    treeNode[] children = new treeNode[10]; // 전화번호라서 자식은 항상 10개
    boolean isEnd;
}

public class Main {
    public static int t, n;
    public static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // t-> n-> words
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            // 여기서 부터 새로운 케이스
            n = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                words.add(br.readLine());
            }
            Collections.sort(words, Comparator.comparingInt(String::length));
            if (validation(words)) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }

            // 마지막 테스트 케이스를 제외하고만 개행 문자를 추가합니다.
            if (i < t - 1) {
                sb.append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    public static boolean validation(List<String> words) {
            treeNode root = new treeNode();
            // word 의 각 단어로 노드 생성
        for (String word : words) {
            // **루트는동일하지만 새로 넣을때마다 항상 루트부터 시작
            treeNode pointer = root;
            for (int k = 0; k < word.length(); k++) {
                int num = Integer.parseInt(String.valueOf(word.charAt(k)));

                // 넣기 전에 현위치노드에서 끝난적 있을떄 체크
                if (pointer.isEnd) {
                    return false;
                }

                if (pointer.children[num] == null) {
                    pointer.children[num] = new treeNode();
                }
                pointer = pointer.children[num];

            }
            // 다 넣었는데 자식 노드 더 가지고 있는경우 체크
            for (int p = 0; p < 10; p++) {
                if (pointer.children[p] != null) {
                    return false;
                }
            }
            pointer.isEnd = true; // 다 넣으면 체크
        }
        return true;
    }
}

