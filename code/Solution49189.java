import java.util.*;
import java.io.*;

public class Solution49189 {
    public static void main(String[] args) throws IOException {
        int n = 6;
        int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
        int answer = 0;

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < edge.length; i++) {
            map.get(edge[i][0]).add(edge[i][1]);
            map.get(edge[i][1]).add(edge[i][0]);
        }
        int[] visited = new int[n + 1]; //깊이 저장
        visited[1] = 1;
        Integer maxDepth = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (int i = 0; i < map.get(current).size(); i++) {
                if (visited[map.get(current).get(i)] == 0) {
                    visited[map.get(current).get(i)] = visited[current] + 1;
                    queue.add(map.get(current).get(i));
                    maxDepth = maxDepth > visited[map.get(current).get(i)]
                            ? maxDepth
                            : visited[map.get(current).get(i)];
                }
            }
        }


        for (int i = 1; i < visited.length; i++) {
            if (maxDepth == visited[i]) {
                answer += 1;
            }
        }

        
        System.out.println(answer);


    }
    
}
