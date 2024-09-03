package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 18,596kb / 시간: 180ms
 *
 */
public class Main_4485_G4_녹색_옷_입은_애가_젤다지_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] cave;
	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};	
	
	public static void main(String[] args) throws IOException {
		int proNum = 0;
		while(true) {
			proNum++;
			N = Integer.parseInt(br.readLine());
			if (N==0) break;
			cave = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("Problem ").append(proNum).append(": ").append(getMinDistance(0, 0, N-1, N-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int getMinDistance(int sr, int sc, int er, int ec) {
		final int minValue = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minRupee = new int[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minRupee[i][j] = minValue;
			}
		}
		
		minRupee[sr][sc] = cave[sr][sc];
		pq.offer(new Node(sr, sc, minRupee[sr][sc]));
		
		while(!pq.isEmpty()) {
			Node stopOver = pq.poll();
			if (visited[stopOver.r][stopOver.c]) continue;
			visited[stopOver.r][stopOver.c] = true;
			if (stopOver.r==er && stopOver.c==ec) return stopOver.weight;
			
			for (int i = 0; i < 4; i++) {
				int nr = stopOver.r+dr[i];
				int nc = stopOver.c+dc[i];
				
				if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if (visited[nr][nc]) continue;
				if (minRupee[nr][nc]>stopOver.weight+cave[nr][nc]) {
					minRupee[nr][nc] = stopOver.weight+cave[nr][nc];
					pq.offer(new Node(nr, nc, minRupee[nr][nc]));
				}
			}
		}
		
		return -1;
		
	}

	private static class Node {
		int r, c, weight;

		public Node(int r, int c, int weight) {
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
	}
}
