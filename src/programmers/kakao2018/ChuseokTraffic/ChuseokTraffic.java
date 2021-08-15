package programmers.kakao2018.ChuseokTraffic;

import java.util.*;

public class ChuseokTraffic {
    public int solution(String[] lines) {
        // request time 순으로 정렬하기 위해 Priority Queue 사용
        PriorityQueue<TrafficTime> pq = new PriorityQueue<>();
        int latestTime = Integer.MIN_VALUE;

        // 가장 마지막으로 response가 일어나는 시간 찾기 → 배열을 만들기 위해 사용
        for (String line : lines) {
            TrafficTime trafficTime = new TrafficTime(line);
            int responseTime = trafficTime.getResponseTime();
            if (latestTime < responseTime) {
                latestTime = responseTime;
            }

            pq.offer(trafficTime);
        }

        /*
         * 풀이과정
         * 1. 가장 이른 request time 부터 가장 늦은 response time + 1초 까지를 배열로 표현한다.
         *    - 계산 편의를 위해 millisecond 를 기준으로 잡았다. (즉, 1초 = 1000으로 표현됨)
         * 2. 각 traffic이 처리되는 것으로 잡히는 구간의 배열 원소값을 +1 해준다.
         *    [예시]
         *    - 만약 21:00:001에 가장 이른 request 가 시작되었고, 처리시간이 2.000초 였다면,
         *      배열의 0번 째 원소부터 3000개의 원소의 값을 +1 (처리시간 2초 + 문제 조건에 의한 초당 처리시간 계산을 위한 1초 = 3초)
         *    - 그 다음 원소가 21:02:501 부터 request 가 시작되었고, 처리시간이 1.000초 였다면,
         *      배열의 2500번 째 원소부터 2000개의 원소의 값을 +1
         *    - 그러면 이 결과로 배열의 2500번째 원소부터 3000번째 원소까지의 값은 +2가 되므로, 최대 초당 처리량은 2가 된다.
         * 3. 배열의 최댓값을 찾아 반환하기
         */

        // 1. 가장 이른 request time 부터 가장 늦은 response time + 1초 까지를 배열로 표현한다.
        // 편의상 가장 이른 시간이 index 0을 갖도록 설정
        int earliestTime = pq.peek().getRequestTime();
        latestTime -= earliestTime;
        int[] counter = new int[latestTime + 1000];

        // 2. 각 traffic이 처리되는 것으로 잡히는 구간의 배열 원소값을 +1 하기
        while (!pq.isEmpty()) {
            TrafficTime trafficTime = pq.poll();
            int requestTime = trafficTime.getRequestTime();
            int responseTime = trafficTime.getResponseTime();

            for (int i = requestTime - earliestTime; i < responseTime - earliestTime + 1000; i++) {
                counter[i]++;
            }
        }

        // 3. 배열의 최댓값 찾기
        int answer = Integer.MIN_VALUE;
        for (int count : counter) {
            if (answer < count) {
                answer = count;
            }
        }

        return answer;
    }
}

class TrafficTime implements Comparable<TrafficTime> {
    // milliseconds 단위를 기준으로 계산 수행
    private final int requestTime;
    private final int responseTime;

    TrafficTime(String trafficInfo) {
        String[] info = trafficInfo.split(" ");
        String timeInfo = info[1];

        int time = 0;

        time += (int)(Double.parseDouble(timeInfo.substring(0, 2)) * 3600 * 1000); // hours
        time += (int)(Double.parseDouble(timeInfo.substring(3, 5)) * 60 * 1000); // minutes
        time += (int)(Double.parseDouble(timeInfo.substring(6,12)) * 1000); // seconds

        this.responseTime = time;
        // 처리시간은 시작시간과 끝시간을 포함하므로 request time = response time - 처리시간 + 1 millisecond
        this.requestTime = time - (int)(Double.parseDouble(info[2].substring(0, info[2].length() - 1)) * 1000) + 1;
    }

    public int getRequestTime() {
        return this.requestTime;
    }

    public int getResponseTime() {
        return this.responseTime;
    }

    // Request time 을 기준으로 오름차순 정렬하도록 설정
    @Override
    public int compareTo(TrafficTime o) {
        return this.getRequestTime() - o.getRequestTime();
    }
}