package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1661_IM_미로_탈출_로봇_BFS {

	static int[][] map; // 맵 좌표
	static boolean[][] visited; // 방문 체크용 배열
	static int sr, sc, er, ec; // 시작 노드와 종료 노드
	static int cn, rn; // 맵 크기
	static int result;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cn = Integer.parseInt(st.nextToken());
		rn = Integer.parseInt(st.nextToken());

		map = new int[rn][cn];
		visited = new boolean[rn][cn];

		st = new StringTokenizer(br.readLine());
		// 0부터 시작하도록 -1 처리해주기
		sc = Integer.parseInt(st.nextToken())-1;
		sr = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;

		// BFS
		for (int i = 0; i < rn; i++) {
			String line = br.readLine();
			for (int j = 0; j < cn; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(result);
	}

	public static void bfs() {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		LinkedList<int[]> q = new LinkedList<>();
		// 시작 좌표를 큐에 넣고, 방문 체크
		q.offer(new int[] { sr, sc, 0 });
		visited[sr][sc] = true;

		int[] node;
		int r, c, nr, nc, dist = 0;

		// 큐가 empty가 아닐 동안 방문
		while (!q.isEmpty()) {
			// 큐에서 노드 하나 가져오기
			node = q.poll();
			r = node[0];
			c = node[1];
			dist = node[2];

			// 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				// 경계 내에 있고 길(0)이고 방문 안한 노드면
				if (nr > -1 && nr < rn && nc > -1 && nc < cn && map[nr][nc] == 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					// 도착점인지 체크
					if (nr == er && nc == ec) {
						result = dist + 1;
						return;
					}
					// 방문(큐에 넣기)&방문 체크
					q.offer(new int[] { nr, nc, dist + 1 });

				}
			}

		}

	}
}
