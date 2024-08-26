package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
		
		dfs(0, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int r, int c) {
		
		
		if (r==N-1 && c==N-1) {
			ans++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			// 벽이거나 방문한 경우 건너뛰기
			if (house[nr][nc]==1) continue;
			if (visited[nr][nc]) continue;
			
			// 대각선으로 갈 경우 세 칸 모두 비어있어야함
			if (i==1) {
				
			}
			
			// 방문 처리
			else if (house[nr][nc]==0 && !visited[nr][nc]) {
				visited[nr][nc]=true;
				dfs(nr, nc);
				visited[nr][nc]=false;
			}
		}
		
	}
	
	private static class State {
		int r;
		int c;
		int br;
		int bc;
		
		public State(int r, int c, int br, int bc) {
			this.r = r;
			this.c = c;
			this.br = br;
			this.bc = bc;
		}
	}
}
