import java.io.*;
import java.util.*;

public class Solution42627 {
    public static void main(String[] args) throws IOException {
        int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 }, { 3, 5 }, { 5, 5 } };
        int answer = 0;

        Queue<Job> queue = new PriorityQueue<>((a, b) -> {
            if (a.start > b.start){
                return 1;
            } else if (a.start < b.start){
                return -1;
            }else{
                return a.time - b.time;
            }
        });

        for (int i = 0; i < jobs.length; i++) {
            queue.add(new Job(jobs[i][0], jobs[i][1]));
        }

        Integer currentTime = 0;
        while (!queue.isEmpty()) {
            Queue<Job> beforeCurrent = new PriorityQueue<>((a, b) -> (a.time - b.time));

            while (!queue.isEmpty() && queue.peek().start <= currentTime) {
                beforeCurrent.add(queue.poll());
            }

            if (beforeCurrent.isEmpty()) {//1. 실행할 job이 없을 때
                currentTime = queue.peek().start + queue.peek().time; //이번 job 종료시간이 총 시간
                answer += queue.poll().time;

            } else { //2. 들어와있는 job이 있을 때
                currentTime += beforeCurrent.peek().time; //이전 완료시간 + 이번 job 길이
                answer += currentTime - beforeCurrent.poll().start; //job 대기시간 + 걸린시간 -> 현재 시간 - start

                while (!beforeCurrent.isEmpty()) {
                    queue.add(beforeCurrent.poll());
                }
            }

        }
        
        answer /= jobs.length;

    }
    
    static class Job {
        int start;
        int time;

        Job(int inputStart, int inputTime) {
            start = inputStart;
            time = inputTime;
        }
    }
    
}
