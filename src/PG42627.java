import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class PG42627 {
    public class Pair implements Comparable<Pair> {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair p) {
            if (this.second < p.second) {
                return -1;
            } else if (this.second == p.second) {
                if (this.first < p.first) {
                    return -1;
                }
            }
            return 1;
        }
    }

    public int solution(int[][] jobs) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int answer = 0;

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])
                    return -1;
                else if (o1[0] == o2[0]) {
                    if (o1[1] < o2[1])
                        return -1;
                }
                return 1;
            }
        });

        int curTime = 0, idx = 0, cnt = 0;
        while (cnt < jobs.length) {
            while (idx < jobs.length && curTime >= jobs[idx][0]) {
                pq.add(new Pair(jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if (!pq.isEmpty()) {
                Pair job = pq.poll();
                cnt++;
                answer += curTime - job.first + job.second;
                curTime += job.second;
            } else
                curTime++;
        }

        return (answer / jobs.length);
    }
}