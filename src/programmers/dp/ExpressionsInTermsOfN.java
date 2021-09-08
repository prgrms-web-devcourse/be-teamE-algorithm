package programmers.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionsInTermsOfN {
    public int solution(int N, int number) {
        // 문제조건에 의해 8개 이상의 N은 사용할 수 없음
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0 ; i < 8; i++) {
            list.add(new HashSet<>());
        }
        list.get(0).add(N); // N을 1개만 사용할 경우, N 자신만 표현 가능

        for (int i = 0; i < 8; i++) {
            // 1. N을 (i + 1)번 이어붙인 경우 where i = 0, 1, 2, ...
            list.get(i).add(Integer.parseInt(repeat(N, i + 1))); //

            // 2. 이전 단계의 결과들를 서로 사칙연산한 결과
            // [N을 1개 사용한 경우] +-*/ [(i + 1) - 1개 사용한 경우]
            // [N을 2개 사용한 경우] +-*/ [(i + 1) - 2개 사용한 경우]
            // ...
            for (int j = 0; j < i; j++) {
                for (int case1 : list.get(j)) {
                    for (int case2 : list.get(i - 1 - j)) {
                        // 정답 확인 로직
                        list.get(i).add(case1 + case2);
                        list.get(i).add(case1 - case2);
                        list.get(i).add(case1 * case2);
                        if (case2 != 0) {
                            list.get(i).add(case1 / case2);
                        }

                    }
                }
            }

            if (list.get(i).contains(number)) {
                return i + 1;
            }
        }

        return -1;
    }

    private String repeat(int N, int times) {
        StringBuilder answer = new StringBuilder();
        String str = String.valueOf(N);
        answer.append(str.repeat(Math.max(0, times)));
        return answer.toString();
    }
}
