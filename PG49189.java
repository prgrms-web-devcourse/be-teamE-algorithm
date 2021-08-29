import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class PG49198 {
    public class Pair implements Comparable<Pair> {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair p) {
            if (this.first < p.first) {
                return -1;
            } else if (this.first == p.first) {
                if (this.second < p.second) {
                    return -1;
                }
            }
            return 1;
        }
    }

    final int INF = Integer.MAX_VALUE;

    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new LinkedList[n + 1];
        int d[] = new int[n + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        int answer = 0;

        // 초기화
        for (int i = 0; i <= n; i++)
            graph[i] = new LinkedList<Integer>();
        Arrays.fill(d, INF);

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // 다익스트라
        d[1] = 0;
        pq.offer(new Pair(0, 1));
        while (!pq.isEmpty()) {
            int value = pq.peek().first;
            int cur = pq.peek().second;
            pq.poll();

            int nextValue = value + 1;
            for (int next : graph[cur]) {
                if (d[next] > nextValue) {
                    d[next] = nextValue;
                    pq.offer(new Pair(nextValue, next));
                }
            }
        }

        // 가장 멀리 떨어진 노드 개수 세기
        for (int i = 2; i <= n; i++) {
            if (max == d[i])
                answer++;
            else if (max < d[i]) {
                max = d[i];
                answer = 1;
            }
        }

        return answer;
    }
}