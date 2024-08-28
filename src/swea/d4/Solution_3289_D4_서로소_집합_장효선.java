package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 103,180 kb / 시간: 448 ms
 *
 */
public class Solution_3289_D4_서로소_집합_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents=new int[N+1];
			makeSet();
			
			// M번 연산
			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (command==0) {  // 0인 경우 연산
					unionSet(a, b);
				} else {  // 1인 경우 같은 집합에 속해있는지 여부 반환
					int aRoot = findSet(a);
					int bRoot = findSet(b);
					
					if (aRoot==bRoot) sb.append(1);
					else sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int findSet(int a) {
		if (a==parents[a]) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}
