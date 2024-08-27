package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 45,972kb / 시간: 424ms
 *
 */

public class Main_2252_G3_줄_세우기_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Integer>[] list;
	static int[] inDegree;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		inDegree = new int[N+1];
		result = new ArrayList<>();
		
		for (int i = 1; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			inDegree[to]++;
		}
		
		topologicalSort();
		
		
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		
		System.out.println(sb);
	}

	private static void topologicalSort() {
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i < N+1; i++) {
			if (inDegree[i]==0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);
			
			for (int i = 0, end = list[cur].size(); i < end ; i++) {
				int ad = list[cur].get(i);
				if (--inDegree[ad]==0) q.offer(ad);
			}
		}
	}
}
