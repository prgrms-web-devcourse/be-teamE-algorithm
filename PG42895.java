import java.util.Arrays;

public class PG42895 {
    public int solution(int N, int number) {
        int dp[] = new int[number + 1];
        int answer = 0;

        Arrays.fill(dp, 9);

        dp[1] = 2;

        for (int i = 2; i <= number; i++) {
            if (i == N)
                dp[N] = 1;
            // else if (i == 11)
            //     dp[11] = 3;
            // else if (i == 12 || i == 13)
            //     dp[i] = 4;
            else {
                for (int j = 1; j <= i / 2; j++)
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
            }
        }

        answer = (dp[number] > 8 ? -1 : dp[number]);

        return answer;
    }
}
/*
N = 2, number = 13

1   2   3   4   5   6   7   8   9   10
---------------------------------------
2   1   3   2   4   3   5   4   6   5

11  12  13
-----------
3   4   4

11 : 22/2       (3)
12 : (22+2)/2   (4)
13 : (22+2+2)/2 (4)

11 넘어가면서 22를 사용 
for (int j = 1; j <= i / 2; j++)
    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]); -> 11부터 해당 X
규칙(?) 어떻게 찾아야하는지 모르겠음...
*/