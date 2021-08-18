
import java.io.*;
import java.util.*;

public class Solution1829 {
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        int m = 6;
        int n = 4; 
        int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
                { 0, 0, 0, 3 } };
        int[] answer = new int[2];

        visited = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            for  (int  j  =   0 ;  j <  n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    answer[0] += 1;
                    answer[1] = bfs(picture, visited, i, j, answer[1], n, m);
                }
            }
        }
        
        
        System.out.println(Arrays.toString(answer) + "\n");
    }

    private static int bfs(int[][] picture, boolean[][] visited, int i, int j,int answer, int n, int m) {

        Queue<Location> queue = new ArrayDeque<>();
        int count = 1;
        visited[i][j] = true;
        queue.add(new Location(i, j));

        int[] dr = { 1, -1, 0, 0 };
        int[] dc = { 0, 0, 1, -1 };


        while (!queue.isEmpty()) {
            Location loc = queue.poll();

            for (int k = 0; k < 4; k++) {
                if (loc.row + dr[k] >= 0 && loc.row + dr[k] < m && loc.col + dc[k] >= 0 && loc.col + dc[k] < n) {
                    if (!visited[loc.row + dr[k]][loc.col + dc[k]]
                            && picture[loc.row + dr[k]][loc.col + dc[k]] == picture[i][j]) {
                        queue.add(new Location(loc.row + dr[k], loc.col + dc[k]));
                        visited[loc.row + dr[k]][loc.col + dc[k]] = true;
                        count += 1;
                    }
                }

            }

        }
        return Math.max(answer, count);

    }


    static class Location {
        int row;
        int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
