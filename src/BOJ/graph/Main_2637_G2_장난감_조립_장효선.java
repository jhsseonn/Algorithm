package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 15,928kb / 메모리: 120ms
 *
 */
public class Main_2637_G2_장난감_조립_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Node>[] list;
	static int[] inDegree, count;
	static boolean[] isProcess;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		inDegree = new int[N+1];
		count = new int[N+1];
		isProcess = new boolean[N+1];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, weight));
			inDegree[to]++;
			isProcess[from] = true;
		}
		
		// 진입 차수*가중치
		getResult();
		
		for (int i = 1; i < isProcess.length; i++) {
			if (!isProcess[i]) {
				System.out.println(i+" "+count[i]);
			}
		}
	}
	
	private static void getResult() {
		Queue<Integer> dq = new LinkedList<>();
		dq.offer(N);
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			for (Node node : list[cur]) {
				if (count[cur]==0) {
					count[node.in] = node.weight;
				} else {
					count[node.in] += count[cur]*node.weight;
				}
				if (--inDegree[node.in]==0) {
					dq.offer(node.in);
				}
			}
			
		}
	}
 	
	static class Node {
		int in, weight;

		public Node(int in, int weight) {
			this.in = in;
			this.weight = weight;
		}
	}
}
