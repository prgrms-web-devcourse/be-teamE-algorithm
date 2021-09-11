package programmers.dfs_bfs;

public class Network {
	private static int totalCount = 0;

	public int solution(int n, int[][] computers) {
		boolean[][] connected = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				connected[i][j] = (computers[i][j] == 1);
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (connected[i][i]) {
				dfs(connected, i);
				count++;
			}

			if (totalCount == n) {
				break;
			}
		}
		return count;
	}

	private void dfs(boolean[][] connected, int i) {
		if (i < 0 || i >= connected.length || !connected[i][i]) {
			return;
		}

		connected[i][i] = false; // mark as visited
		totalCount++;

		for (int j = 0; j < connected.length; j++) {
			if (connected[i][j]) {
				dfs(connected, j);
			}
		}
	}
}