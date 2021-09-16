import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class PG49191 {
    public int solution(int n, int[][] results) {
        List<Integer>[] graph = new LinkedList[n + 1]; // win -> lose
        int[] countOfWins = new int[n + 1]; // 각 노드가 이긴 횟수
        int[] countOfLost = new int[n + 1]; // 각 노드가 진 횟수

        for (int i = 0; i <= n; i++)
            graph[i] = new LinkedList<Integer>();

        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];

            graph[win].add(lose);
        }

        // 현재 노드에서 countOfWins(나가는 간선) + countOfLose(들어오는 간선) = n-1 -> 순위 결정!

        for (int i = 1; i <= n; i++) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[n + 1];

            visited[i] = true;
            q.offer(i);
            while (!q.isEmpty()) {
                int p = q.poll();
                for (int node : graph[p]) {
                    if (!visited[node]) {
                        visited[node] = true;
                        q.add(node);

                        countOfWins[i]++;
                        countOfLost[node]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (countOfWins[i] + countOfLost[i] == n - 1)
                answer++;
        }

        return answer;
    }
}
