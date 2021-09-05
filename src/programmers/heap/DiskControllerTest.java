package programmers.heap;

import static org.junit.jupiter.api.Assertions.*;

class DiskControllerTest {
	DiskController obj = new DiskController();

	@org.junit.jupiter.api.Test
	void solution() {
		assertEquals(9, obj.solution(new int[][] {
				{0, 3},
				{1, 9},
				{2, 6}
		}));
	}
}