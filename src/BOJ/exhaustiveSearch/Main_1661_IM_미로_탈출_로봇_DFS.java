package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1661_IM_미로_탈출_로봇_DFS {

	static int[][] map; // 맵 좌표
	static boolean[][] visited; // 방문 체크용 배열
	static int sr, sc, er, ec; // 시작 노드와 종료 노드
	static int cn, rn; // 맵 크기
	static int result;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int minDist = Integer.MAX_VALUE;
	static int[][] dists;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cn = Integer.parseInt(st.nextToken());
		rn = Integer.parseInt(st.nextToken());

		map = new int[rn][cn];
		visited = new boolean[rn][cn];
		dists = new int[rn][cn];

		st = new StringTokenizer(br.readLine());
		// 0부터 시작하도록 -1 처리해주기
		sc = Integer.parseInt(st.nextToken()) - 1;
		sr = Integer.parseInt(st.nextToken()) - 1;
		ec = Integer.parseInt(st.nextToken()) - 1;
		er = Integer.parseInt(st.nextToken()) - 1;

		// BFS
		for (int i = 0; i < rn; i++) {
			String line = br.readLine();
			for (int j = 0; j < cn; j++) {
				map[i][j] = line.charAt(j) - '0';
				dists[i][j] = Integer.MAX_VALUE;
			}
		}

		dfs(sr, sc, 0);
		System.out.println(minDist);
	}

	public static void dfs(int r, int c, int dist) {
		if (dists[r][c] <= dist) return;
		dists[r][c] = dist;
		if (dist >= minDist) return; // 지금까지의 거리가 이미 이전의 목적지까지 최솟값을 넘어섰다면 멈추기
		if (r == er && c == ec) {
			minDist = Math.min(minDist, dist);
			return;
		}
		
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr > -1 && nr < rn && nc > -1 && nc < cn && map[nr][nc] == 0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, dist + 1);
				visited[nr][nc] = false;
			}
		}

	}
}
