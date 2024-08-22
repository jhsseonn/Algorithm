package BOJ.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_G2_빵집_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static boolean[][] isSelected;
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isSelected = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 탐색
		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0);
		}
		
		int count = 0;
		for (int j = 0; j < R; j++) {
			if (isSelected[j][C-1]) {
				count+=1;
			}
		}
		
		System.out.println(count);
	}
	
	private static void dfs(int r, int c) {
		if (c==C-1) {
			// 마지막 열 체크
			flag = true;
//			isSelected[r][c] = true;
			return;
		}
		
		for (int j = 0; j < 3; j++) {
			int nr = r+dr[j];
			int nc = c+dc[j];
			
			if (nr>-1 && nr<R && nc>-1 && nc<C && !isSelected[nr][nc] && map[nr][nc]=='.') {
				if (flag) return;
				isSelected[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}

}
