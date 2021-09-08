package programmers.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTriangleTest {
	IntegerTriangle obj = new IntegerTriangle();

	@Test
	void solution() {
		assertEquals(30, obj.solution(new int[][] {
				{7},
				{3, 8},
				{8, 1, 0},
				{2, 7, 4, 4},
				{4, 5, 2, 6, 5}
		}));
	}
}