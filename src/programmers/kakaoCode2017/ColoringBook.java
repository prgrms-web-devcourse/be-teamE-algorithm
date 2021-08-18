package programmers.kakaoCode2017;

public class ColoringBook {
    public int[] solution(int m, int n, int[][] picture) {
        /**
         * 0. DFS 방식으로 풀이
         * 1. (0, 0) 부터 순차적으로 picture에 속한 모든 원소를 순회
         * 2. 순회 중 어떤 (i, j)번 째 임의의 원소에 대해 picture[i][j] != 0 이고, visited[i][j] == false이면 아직 게산하지 않은 영역이다.
         *    따라서, 이 (i, j)번째 원소을 시작으로 새로운 영역 탐색을 시작한다. (영역 갯수 +1)
         *    (i, j)번째 원소부터 DFS 알고리즘에 들어가는데, 이 때에는 picture[i][j]번 째 원소와 새로 들어가는 원소의 값이 같고, visited[i][j]가 false이면 같은 영역이다. (영역의 넓이 +1)
         *          * 단 영역의 기준으로 삼는 임의의 정수는 dfs 진입 전에 지정되므로 맨 처음 dfs 함수가 call e되는 시점에는 (i, j)번 째 원소와  (i, j)번 째 원소를 비교하므로 무조건 성립한다. (시작지점이므로, 영역의 넓이는 최소 1이다)
         * 3. (i, j)번 째 원소 확인이 끝났으면 해당 원소의 상하좌우에 있는 원소도 확인한다. 이를 더이상 주변에 같은 기준값(영역값)을 갖는 원소가 없을 떄 까지 반복한다.
         * 4. 기존에 찾은 최대 영역의 넓이와 새로 찾은 영역의 넓이 중 최대 넓이를 저장한다.
         * 5. picture의 마지막 원소까지 순회를 마쳤으면 지금까지 저장한 영역의 갯수와 최대 영역의 넓이를 반환한다.
         */

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(visited, picture, i, j, picture[i][j]));
                }
            }
        }

        return new int[] {numberOfArea, maxSizeOfOneArea};
    }

    // 상하좌우 이동용
    int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private int dfs(boolean[][] visited, int[][] picture, int i, int j, int value) {
        int size = 0;
        if (i < 0 || j < 0 || i >= picture.length || j >= picture[0].length || visited[i][j]) {
            return size;
        }

        if (value == picture[i][j]) {
            size++;
            visited[i][j] = true;

            for (int[] direction : directions) {
                size += dfs(visited, picture, i + direction[0], j + direction[1], value);
            }
        }

        return size;
    }
}
