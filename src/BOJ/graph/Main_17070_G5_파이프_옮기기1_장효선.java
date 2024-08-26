package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리: 168,264kb / 시간: 464ms
 */
public class Main_17070_G5_파이프_옮기기1_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[][] house;
	static boolean[][][] visited;
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		visited = new boolean[N][N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(new Pipe(0, 1, 0));
		
		System.out.println(ans);
	}

	private static void dfs(Pipe pipe) {
		int r = pipe.r;
		int c = pipe.c;
		int state = pipe.state;
		
		if (r==N-1 && c==N-1) {
//			System.out.println(r+" "+c+" "+state);
			ans++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];

			if (nr<0 || nr>=N || nc<0 || nc>=N) continue;

			// 벽인 경우 건너뛰기
			if (house[nr][nc]==1) continue;

			// 파이프가 가로로 있었던 경우 아래로 갈 수 없음, 파이프가 세로로 있었던 경우 옆으로 갈 수 없음
			// 파이프가 대각선으로 있는 경우 세칸 모두 비어 있어야만 이동할 수 있음
			if (i==0 && state==2) continue;
			if (i==2 && state==0) return;
			if (i==1) {
				if (house[nr-1][nc]!=0 || house[nr][nc-1]!=0) continue;
			}

			// 방문한 경우 건너뛰기
			if (visited[nr][nc][i]) continue;

			// 방문 처리
			if (house[nr][nc]==0 && !visited[nr][nc][i]) {
				visited[nr][nc][i]=true;
				dfs(new Pipe(nr, nc, i));
				visited[nr][nc][i]=false;
			}
		}
	}
	
	private static class Pipe {
		int r;
		int c;
		int state;
		
		public Pipe(int r, int c, int state) {
			this.r = r;
			this.c = c;
			this.state = state;
		}
	}
}
