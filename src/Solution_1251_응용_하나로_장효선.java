package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_응용_하나로_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static double E;
	static Island[] islands;
	static int[] parents;
	static Edge[] edges;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			edges = new Edge[N*(N-1)/2];
			
			StringTokenizer xs = new StringTokenizer(br.readLine());
			StringTokenizer ys = new StringTokenizer(br.readLine());
			islands = new Island[N];
			E = Double.parseDouble(br.readLine());
			
			for (int i = 0; i < N; i++) {
				double x = Integer.parseInt(xs.nextToken());
				double y = Integer.parseInt(ys.nextToken());
				islands[i] = new Island(x, y);
			}
			
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					double xd = Math.pow(islands[i].x-islands[j].x, 2);
					double yd = Math.pow(islands[i].y-islands[j].y, 2);
					double L = (xd+yd)*E;
					edges[i] = new Edge(i, j, L);
				}
			}
			
			Arrays.sort(edges);
			
			sb.append("#").append(test_case).append(" ").append((long)getBridge()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int getBridge() {
		make();
		
		int result = 0, cnt = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				result+=edge.L;
				if (++cnt==N-1) break;
			}
		}
		return result+=0.5;
	}
	
	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = -1;
		}
	}
	
	private static int findSet(int a) {
		if (parents[a]<0) return a;
		return parents[a]=findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		
		parents[aRoot]+=parents[bRoot];
		parents[bRoot]= aRoot;
		return true;
	}
	
	private static class Island {
		double x, y;

		public Island(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double L;

		public Edge(int start, int end, double L) {
			this.start = start;
			this.end = end;
			this.L = L;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.L, o.L);
		}
	}

}
