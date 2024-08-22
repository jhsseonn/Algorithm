package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1861_D4_정사각형_방_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, max, minNum, cnt;
	static int[][] rooms;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			visited = new boolean[N][N];
			max = 0;
			minNum = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 1;
					visited[i][j] = true;
					move(i, j, rooms[i][j]);
					for (int k = 0; k < N; k++) {
						Arrays.fill(visited[k], false);
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(minNum).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static void move(int r, int c, int start) {
		if (cnt == N * N) {
			if (cnt==max) minNum = Math.min(minNum, start);
			else {				
				max = cnt;
				minNum = start;
			}
			return;
		}


		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc] &&(rooms[nr][nc] - rooms[r][c] == 1)) {
				visited[nr][nc] = true;
				cnt++;
				move(nr, nc, start);
				visited[nr][nc] = false;
			}
		}

		if (max < cnt) {
			max = cnt;
			minNum = start;
		}

		if (max==cnt) {
			minNum = Math.min(minNum, start);
		}
	}

}
