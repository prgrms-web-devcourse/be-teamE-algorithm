import java.io.*;
import java.util.*;

public class Solution49191 {
    public static void main(String[] args) throws IOException {
        int n = 5;
        int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
        int answer = 0;

        int[][] vsmap = new int[n+1][n+1];

        for (int i = 0; i < results.length; i++) {
            vsmap[results[i][0]][results[i][1]] = 1;
            vsmap[results[i][1]][results[i][0]] = 2;

            //4번 선수가 진 목록은 3번선수도 짐
            for (int j = 1; j <= n; j++) {
                if (vsmap[results[i][0]][j] == 2) {
                    vsmap[results[i][1]][j] = 2;
                }
            }

            // 3번선수가 이긴 목록은 4번선수도 이김
            for (int j = 1; j <= n; j++) {
                if (vsmap[results[i][1]][j] == 1) {
                    vsmap[results[i][0]][j] = 1;
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            Integer count = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && vsmap[i][j] != 0) {
                    count += 1;
                }
            }
            
            if (count == n - 1)
                answer += 1;
        }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j <= n; j++) {
        //         System.out.print(vsmap[i][j]+" ");
        //     }
        //     System.out.println(" ");
        // }

        System.out.println(answer);
    }
}
