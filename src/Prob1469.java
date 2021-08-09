import java.util.*;

public class Prob1469 {
    public static int[] answer;
    public static boolean termi = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] nums = new int[N];
        answer = new int[N * 2];

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);

        // permutation 이용
        permutation(nums, 0, N, N);
        sc.close();
    }

    public static void permutation(int[] nums, int depth, int n, int r) {
        if (termi) {
            return;
        }

        if (depth == r) {
            // 여기서 찾기
            if (check(nums)) {
                termi = true;
                for (int i = 0; i < answer.length - 1; i++) {
                    System.out.print(answer[i] + " ");
                }
                System.out.print(answer[answer.length - 1]);
            }
        }
        for (int i = depth; i < n; i++) {
            swap(nums, depth, i);
            permutation(nums, depth + 1, n, r);
            swap(nums, depth, i);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean check(int[] nums) {
        int[] currentAnswer = new int[2 * nums.length];
        Arrays.fill(currentAnswer, -1);

        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] + 1 >= 2 * nums.length) {
                // 범위 초과
                return false;
            }

            if (currentAnswer[i] != -1 || currentAnswer[i + nums[i] + 1] != -1) {
                // 이미 들어간 값이 있음
                return false;
            }

            currentAnswer[i] = nums[i];
            currentAnswer[i + nums[i] + 1] = nums[i];
        }

        answer = currentAnswer;
        return true;
    }
}
