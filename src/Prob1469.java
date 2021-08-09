import java.util.*;

public class Prob1469 {
    public static int[] answer;
    public static boolean termi = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Integer[] nums = new Integer[N];
        answer = new int[N * 2];
        // 중복숫자 검사.
        Set<Integer> alreadyIn = new HashSet<>();
        boolean impossible = false;

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            if (!alreadyIn.add(nums[i])) {
                impossible = true;
            }
        }

        if (impossible) {
            System.out.println(-1);
            sc.close();
            return;
        }
        // Arrays.sort(nums); // 사전순? 2가 10보다 앞에 오는 경우

        // 사전순을 어떻게 정의?
        // 10이 2보다 앞에 오는 경우
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String n1 = String.valueOf(o1);
                String n2 = String.valueOf(o2);
                int i1 = n1.charAt(0) - '0';
                int i2 = n2.charAt(0) - '0';
                return i1 - i2;
            }
        };
        Arrays.sort(nums, comp);


        // permutation 이용
        permutation(nums, 0, N, N); // nPn

        if (!termi) {
            System.out.println(-1);
        }

        sc.close();
    }

    public static void permutation(Integer[] nums, int depth, int n, int r) { // nPr
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

    public static void swap(Integer[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean check(Integer[] nums) {
        int[] currentAnswer = new int[2 * nums.length];
        Arrays.fill(currentAnswer, -1);

        for (int num : nums) {
            int firstEmptySpace = getFirstEmptySpace(currentAnswer);

            if (firstEmptySpace == -1 || firstEmptySpace + num + 1 >= 2 * nums.length) {
                // 이미 꽉차있거나 (발생하면 안됨)
                // 범위 초과
                return false;
            }

            if (currentAnswer[firstEmptySpace] != -1 || currentAnswer[firstEmptySpace + num + 1] != -1) {
                // 이미 들어간 값이 있음
                return false;
            }

            currentAnswer[firstEmptySpace] = num;
            currentAnswer[firstEmptySpace + num + 1] = num;
        }

        answer = currentAnswer;
        return true;
    }

    public static int getFirstEmptySpace(int[] currentAnswer) {
        for (int i = 0; i < currentAnswer.length; i++) {
            if (currentAnswer[i] == -1) {
                return i;
            }
        }
        return - 1;
    }
}
