package programmers.dfs_bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class TargetNumber {
    public int solution(int[] numbers, int target) {
        /**
         * Queue 자료구조 사용시 대체적으로 LinkedList보다 ArrayDeque의 성능이 좋음
         */
        Queue<Integer> queue = new ArrayDeque<>();
        
        /**
         * 덧셈과 뺄셈의 항등원을 Root 값으로 설정
         */
        queue.offer(0);
        
        /**
         * 모든 Leaf Node 들에 대해서 주어진 숫자들과 차례대로 덧셈, 뺄셈 연산을 진행한다. 그 결과는 다시 해당 node의 child node가 된다.
         * 
         * 첫 번째 턴의 leaf node는 0 (root)이고, 첫 번째 주어진 숫자는 1이다. 
         * 이 결과는 0 + 1 = 1, 0 - 1 = -1이다.
         * 
         * 두 번째 턴의 모든 leaf node들은 1, -1이고, 두 번째 주어진 숫자는 2이다.
         * 이 결과는 1 + 2, 1 - 2, -1 + 2, -1 - 2 이다.
         */
        for (int i = 0; i < numbers.length; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int currentValue = queue.poll();
                queue.offer(currentValue + numbers[i]);
                queue.offer(currentValue - numbers[i]);
            }
        }

        /**
         * 최종 결과 확인
         */
        int count = 0;
        while (!queue.isEmpty()) {
            if (queue.poll() == target) {
                count++;
            }
        }

        return count;
    }
}
