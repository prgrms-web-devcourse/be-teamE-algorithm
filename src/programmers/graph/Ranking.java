package programmers.graph;

public class Ranking {
	public int solution(int n, int[][] results) {
		// initial setup
		int[][] matchResults = new int[n][n];
		for (int i = 0; i < n; i++) {
			matchResults[i][i] = 1;
		}
		for (int[] result : results) {
			int winner = result[0] - 1;
			int loser = result[1] - 1;

			matchResults[winner][loser] =  1;
			matchResults[loser][winner] = -1;
		}

		// a → b, b → c 이면 a → c
		for (int b = 0; b < n; b++) {
			for (int a = 0; a < n; a++) {
				if (matchResults[a][b] > 0) {
					// a가 b에게 이겼지만
					for (int c = 0; c < n; c++) {
						if (matchResults[a][c] == 0 && matchResults[b][c] > 0) { // a가 c를 이겼는지는 아직 모를 때, b가 c에게 이겼으면 a도 c에게 승리
							matchResults[a][c] = 1;
							matchResults[c][a] = -1;
						}
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (matchResults[i][j] == 0) {
					break;
				}
				count++;
			}

			answer += (count / n);
		}

		return answer;
	}
}