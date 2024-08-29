package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 111,440 kb / 시간: 2,416ms
 *
 */
public class Solution_3124_D4_최소_스패닝_트리_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			long result = 0;
			long cnt = 0;
			
			// 간선 정보 입력받기
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(A, B, C);			
			}
			
			// 그래프 탐색을 위해 간선 정렬 및 단위 서로소 집합 생성
			Arrays.sort(edges);
			make();
			
			// 탐색 시작
			for (Edge edge : edges) {
				if (union(edge.a, edge.b)) {
					result+=edge.c;
					if (++cnt==V-1) break;
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void make() {
		parents = new int[V+1];
		Arrays.fill(parents, -1);
	}
	
	private static int findSet(int a) {
		if (parents[a]<0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		
		parents[aRoot]+=parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static class Edge implements Comparable<Edge>{
		int a, b, c;

		public Edge(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}

}
