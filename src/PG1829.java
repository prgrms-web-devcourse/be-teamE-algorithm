class PG1829 {
    final int dir[][]= {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 방향 : 상 우 하 좌
    int M, N;
    int cnt; // 한 영역의 넓이
    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                cnt = 0; // 한 영역의 넓이 초기화
                if(!visited[i][j] && picture[i][j]>0){
                    numberOfArea++;
                    dfs(i, j, picture[i][j], visited, picture);
                }
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    public void dfs(int x, int y, int color, boolean[][] visited, int[][] picture){ // color : 현재 영역의 색깔(숫자)
        visited[x][y] = true;
        cnt++;

        for(int i=0;i<4;i++){
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            if(!isOnPicture(nextX, nextY)) continue;

            if(!visited[nextX][nextY] && picture[nextX][nextY] == color)
                dfs(nextX, nextY, color, visited, picture);
        }

    }
    public boolean isOnPicture(int x, int y){ // 범위 확인
        if(0<=x && x<M && 0<=y && y<N)
            return true;
        return false;
    }
}