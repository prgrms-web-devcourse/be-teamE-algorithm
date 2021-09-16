package programmers.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankingTest {
	Ranking obj = new Ranking();

	@Test
	@DisplayName("에제문제")
	void solution() {
		assertEquals(2, obj.solution(5, new int[][] {
				{4, 3},
				{4, 2},
				{3, 2},
				{1, 2},
				{2, 5}
		}));
	}
}