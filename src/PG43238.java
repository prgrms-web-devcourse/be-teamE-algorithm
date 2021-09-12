import java.util.Arrays;

class PG43238 {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        int timesLen = times.length;

        Arrays.sort(times);

        long left = times[0];
        long right = times[timesLen - 1] * (long)n;
        long sum = 0;

        while (left <= right) {
            sum = 0;
            long mid = (left + right) / 2;

            for (int i = 0; i < timesLen; i++)
                sum += (mid / times[i]);

            if (sum < n)
                left = mid + 1;
            else {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] times = { 1, 2, 3 };

        /*
        3 [1, 1, 1] 1
        3 [1, 2, 3] 2
        */

        System.out.println(new PG43238().solution(n, times));
    }
}