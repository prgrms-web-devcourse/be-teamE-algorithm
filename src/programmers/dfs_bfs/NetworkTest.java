package programmers.dfs_bfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
	Network obj = new Network();

	@Test
	void solution() {
		assertEquals(2, obj.solution(3, new int [][] {
				{1, 1, 0},
				{1, 1, 0},
				{0, 0, 1}
		}));
	}

	@Test
	void solution2() {
		assertEquals(1, obj.solution(3, new int[][] {
				{1, 1, 0},
				{1, 1, 1},
				{0, 1, 1}
		}));
	}
}