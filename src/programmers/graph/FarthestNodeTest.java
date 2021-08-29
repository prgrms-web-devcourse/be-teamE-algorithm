package programmers.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarthestNodeTest {
	FarthestNode obj = new FarthestNode();

	@Test
	void solution() {
		assertEquals(3, obj.solution(6, new int[][] {
				{3, 6},
				{4, 3},
				{3, 2},
				{1, 3},
				{1, 2},
				{2, 4},
				{5, 2}
		}));
	}
}