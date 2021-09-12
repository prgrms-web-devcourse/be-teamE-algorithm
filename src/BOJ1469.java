import java.io.*;
import java.util.*;

public class BOJ1469 {
    static int n; 
    static int[] x;
    static List<String> list = new ArrayList<>(); // 모든 경우의 수를 담는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        x = new int[n];
        int[] s = new int[2 * n];
        Arrays.fill(s, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            x[i] = Integer.parseInt(st.nextToken());

        // --- 입력 끝 ---

        solve(0, s);

        if (list.isEmpty())
            System.out.println(-1);
        else {
            list.sort(Comparator.naturalOrder()); // 리스트 오름차순 정렬

            System.out.println(list.get(0)); // 사전 순으로 가장 빠른 것 출력
        }
    }

    public static void solve(int cur, int[] s) { // cur : x배열의 index, s배열 : 숌 사이 수열(s)
        if (cur == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2 * n; i++) {
                if (s[i] == -1) return;
                sb.append(Integer.toString(s[i]) + ' ');
            }
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < 2 * n - (x[cur] + 1); i++) {
            if (s[i] == -1 && s[i + x[cur] + 1] == -1) { 
                s[i] = s[i + x[cur] + 1] = x[cur];
                solve(cur + 1, s);
                s[i] = s[i + x[cur] + 1] = -1;
            }
        }
    }
}

