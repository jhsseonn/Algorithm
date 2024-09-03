package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 14,692kb / 시간: 116ms
 *
 */
public class Main_10971_S2_외판원_순회2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] adjMatrix;
	static int N, minDistance, start;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		minDistance = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (adjMatrix[i][j]!=0) {
					start = i;
					dfs(j, new boolean[N], adjMatrix[i][j], 1);
				}
			}
		}
		
		System.out.println(minDistance);
		
	}
	
	private static void dfs(int cur, boolean[] visited, int sum, int cnt) {
		// 현재까지 탐색한 경로의 합이 이전 최단 경로보다 크거나 같은 경우 탐색을 중단한다
		if (sum>=minDistance && cnt<N-1) return;
		
		// 마지막 노드까지 갔으면 다시 처음 노드로 돌아가는 거리를 더해 minDistance를 갱신한다.
		if (cnt==N-1 && adjMatrix[cur][start]!=0) {
			sum += adjMatrix[cur][start];
			minDistance = Math.min(minDistance, sum);
			return;
		}
		
		visited[cur] = true;  // 자기자신은 갈 수 없음
		
		for (int i = 0; i < N; i++) {
			if (adjMatrix[cur][i]==0 || visited[i]) continue;
			if (i==start && cnt<N-1) continue;
			
			visited[i] = true;
			dfs(i, visited, sum+adjMatrix[cur][i], cnt+1);
			visited[i] = false;
		}
	}
}
