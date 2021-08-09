import java.io.*;
import java.util.*;

public class q1469 {
    static boolean flag = false;
    static Integer N;
    static Integer[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //input
        N = Integer.parseInt(br.readLine());
        Integer[] X = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }
        result = new Integer[N*2];

        //오름차순 정렬
        Arrays.sort(X);

        //가장 큰 수의 간격만큼의 크기보다 작을 때 종료
        if (X[N-1] + 1 < N) {
            System.out.println("-1");
            return;
        }

        Integer[] visited = new Integer[N * 2];
        for (int i = 0; i < N * 2 - (X[0] + 1); i++) {
            /*
            가장 작은 수를 기준으로 
            1 _ 1 _ _ _
            _ 1 _ 1 _ _
            _ _ 1 _ 1 _
            _ _ _ 1 _ 1
            이렇게 4가지 경우를 탐색
            */

            //dfs()에서 숫자의 위치 기록을 위해서 -1로 초기화
            visited = new Integer[N * 2];
            for (int j = 0; j < visited.length; j++) {

                visited[j] = -1;
            }
            visited[i] = X[0];
            visited[i + X[0] + 1] = X[0];

            dfs(X, visited, 1);
        }
        if (result[0] == null) {
            System.out.println("-1");
            return;
        } else {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]+" ");
            }
        }

        br.close();
    }
    
    /*
    X : input값으로 들어온 수열(오름차순 정렬)
    visited : 재귀를 들어가면서 저장한 숫자 기록
    currentX : 수열 X에서 이번 재귀에서 기록해야 할 X값의 index
    
    종료 조건 : 이미 저장된 result보다 사전 순으로 뒤일 때 (저장X 종료)
                숌 사이 수열을 완성했을 때(저장 O 종료)
    
    모든 경우의 수를 체크하면서 사전 순으로 뒤일 경우 연산을 하지 않고 종료한다.
    */
    static void dfs(Integer[] X, Integer[] visited, Integer currentX) {
        /* 
        이미 저장된 수열보다 
        사전순으로 느린 경우 탐색 X
        */
        if (result[0] != null) {//수열이 저장 됐을 때 검사 시작
            for (int i = 0; i < N * 2; i++) {
                if (visited[i] != -1) {
                    if (visited[i] > result[i]) {
                        return;
                    } else if(visited[i] == result[i]){
                    } else {
                        break;
                    }
                }else{ break; }
            }
        }
        

        if (currentX == N) {
            result = visited.clone();
            flag = true;
            return;
        }

        
        Integer InputX = X[currentX];//기록해야할 값


        for (int i = 0; i < visited.length; i++) {
            //2자리 모두 아직 기록되지 않은 자리일 경우 dfs 실행, 
            if (visited[i] == -1 && (i + InputX+1)<(N*2)) {
                if (visited[i + InputX + 1] == -1) {
                    //2자리에 모두 값을 넣을 수 있을 경우 다음 재귀로
                    visited[i] = InputX;
                    visited[i + InputX + 1] = InputX;
                    dfs(X, visited, currentX + 1);

                    //다시 원래대로 돌려놓음
                    visited[i] = -1;
                    visited[i + InputX + 1] = -1;
                }
            }
        }

        return;
    }

}
