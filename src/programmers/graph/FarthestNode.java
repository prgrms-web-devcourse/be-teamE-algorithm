package programmers.graph;

import java.util.*;

public class FarthestNode {
	public int solution(int n, int[][] edge) {
		Queue<Integer> queue = new LinkedList<>();

		boolean[][] graph = new boolean[n][n];
		// 자기 자신과도 연결된 것으로 가정.
		for (int i = 0; i < n; i++) {
			graph[i][i] = true;
		}

		// 세로축을 각 노드, 가로축을 연결된 노드로 볼 때의 연결 정보 업데이트
		for (int[] connection : edge) {
			graph[connection[0] - 1][connection[1] - 1] = true;
			graph[connection[1] - 1][connection[0] - 1] = true;
		}

		// 스타팅 지점 준비
		Map<Integer, Integer> results = new HashMap<>(); // key: 거리, value: 해당 거리만큼 떨어진 nodes의 수
		Set<Integer> visited = new HashSet<>(); // 이미 방문한 노드들의 집합을 저장
		int currentDistance = 0;
		queue.offer(0); // 1번 노드
		visited.add(0);

		// 탐색
		do {
			int size = queue.size();
			currentDistance++;

			for (int j = 0; j < size; j++) {
				int currentNode = queue.poll();

				boolean[] nodesConnectedToCurrentNode = graph[currentNode];

				for (int i = 0; i < nodesConnectedToCurrentNode.length; i++) {
					if (nodesConnectedToCurrentNode[i] && visited.add(i)) {
						queue.offer(i);
					}
				}
			}

			results.put(currentDistance, queue.size());
		} while (!queue.isEmpty());

		// 탐색 종료시에 currentDistance는 `가장 먼 노드까지의 거리 + 1` 값을 가짐
		// while문 종료 조건에 의해 가장 먼 거리에 있는 노드가 queue에 있으면 루프를 한번 더 돌며 currentDistance가 +1 되기 때문.
		return results.get(--currentDistance);
	}
}
