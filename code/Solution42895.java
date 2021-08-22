
import java.io.*;
import java.util.HashSet;

public class Solution42895 {
    static int result = 8;
    

    public static void main(String[] args) throws IOException {
        int N = 5;
        int number = 12;
        int answer = 0;


        String func = String.valueOf(N);
        dfs(N, number, func, N, 1);
        answer = result >= 8 ? -1 : result;

        System.out.println(answer);
    }

    
    

    static void dfs(int N, int number, String func, int dsum, int depth) {
        if (depth > result) {
            return;
        }

        if (dsum == number) {
            result = depth;
            return;
        }

        String[] df = { " + " + N, " - " + N, " / " + N, " * " + N, "" + N };

        Integer sum = 0;
        for (int i = 0; i < 5; i++) {
            sum = solveFunc(func + df[i]);
            dfs(N, number, func + df[i], sum, depth + 1);
        }

    }

    private static Integer solveFunc(String func) {
        String[] splitFunc = func.split(" ");
        Integer sum = Integer.parseInt(splitFunc[0]);
        for (int i = 1; i < splitFunc.length; i += 2) {
            switch (splitFunc[i]) {
                case "+" -> {
                    sum += Integer.parseInt(splitFunc[i + 1]);
                }
                case "-" -> {
                    sum -= Integer.parseInt(splitFunc[i + 1]);
                }
                case "/" -> {
                    sum /= Integer.parseInt(splitFunc[i + 1]);
                }
                case "*" -> {
                    sum *= Integer.parseInt(splitFunc[i + 1]);
                }
            }
        }

        return sum;
    }

}
