package programmers.dp;

public class IntegerTriangle {
	public int solution(int[][] triangle) {
		for (int i = triangle.length - 1; i > 0; i--) {
			int[] currentLine = triangle[i];
			int[] nextLine = triangle[i - 1];

			for (int j = 0; j < nextLine.length; j++) {
				int left = currentLine[j];
				int right = currentLine[j + 1];
				nextLine[j] = Math.max(nextLine[j] + left, nextLine[j] + right);
			}
		}

		return triangle[0][0];
	}
}
