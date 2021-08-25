package programmers.binarySearch;

import java.util.Arrays;

public class BorderControl {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long minimum = 1;
        long maximum = (long) times[times.length - 1] * n;
        long mid = (minimum + maximum) / 2;

        long number = count(mid, times);
        while (minimum < maximum) {
            if (number >= n) {
                maximum = mid;
            } else {
                minimum = mid + 1;
            }
            mid = (minimum + maximum) / 2;
            number = count(mid, times);
        }

        return mid;
    }

    private long count(long t, int[] times) {
        // 주어진 시간 t동안에 입국 심사 처리가 가능한 사람의 수를 반환
        long answer = 0;
        for (int time : times) {
            answer += (t / time);
        }
        return answer;
    }
}
