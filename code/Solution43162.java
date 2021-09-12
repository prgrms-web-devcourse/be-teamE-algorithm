import java.io.*;
import java.util.*;

public class Solution43162 {
    public static void main(String[] args) throws IOException {
        int n = 3;
        int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        int answer = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers, visited, n, i);
                answer += 1;
            }
        }

        System.out.println(answer);

    }

    public static void dfs(int[][] computers, boolean[] visited, int n, Integer current) {
        visited[current] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[current][i] == 1) {
                dfs(computers, visited, n, i);
            }
        }

    }
}
