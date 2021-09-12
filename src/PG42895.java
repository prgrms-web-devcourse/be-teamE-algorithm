public class PG42895 {
    int n, target;
    int answer = Integer.MAX_VALUE;

    public int solution(int N, int number) {
        n = N;
        target = number;

        dfs(0, 0);

        return (answer > 8 ? -1 : answer);
    }

    void dfs(int cur, int depth) {
        if (depth > 8)
            return;

        if (target == cur) {
            answer = Math.min(answer, depth);
            return;
        }

        int nn = n;
        for (int i = 1; i <= 8; i++) {
            dfs(nn, depth + i);
            dfs(cur + nn, depth + i);
            dfs(cur - nn, depth + i);
            dfs(cur * nn, depth + i);
            dfs(cur / nn, depth + i);
            nn = nn * 10 + n;
        }
    }
}