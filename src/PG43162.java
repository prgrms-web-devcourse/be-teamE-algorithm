class PG43162 {
    boolean visited[];

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers, i);
                answer++;
            }
        }

        return answer;
    }

    void dfs(int[][] computers, int current) {
        for (int i = 0; i < computers.length; i++) {
            if (i == current)
                continue;
            if (computers[current][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(computers, i);
            }
        }
    }
}