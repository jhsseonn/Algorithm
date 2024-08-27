package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_1267_D6_작업순서_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int V;
	static ArrayList<Integer>[] list;
	static int[] inDegree;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			list = new ArrayList[V+1];
			inDegree = new int[V+1];
			result = new ArrayList<>();
			
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
						
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
				inDegree[to]++;
			}
			
			getTopologicalSort();
			
			// 결과 출력
			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < result.size(); i++) {
				sb.append(result.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 위상 정렬
	private static void getTopologicalSort() {
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= V; i++) {
			if (inDegree[i]==0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);
			
			for (int i = 0, end=list[cur].size(); i < end; i++) {
				int ad = list[cur].get(i);
				if (--inDegree[ad]==0) q.offer(ad);
			}
		}
	}

}
