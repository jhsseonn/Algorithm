package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 14,564kb / 시간: 112ms
 *
 */
public class Main_1149_S1_RGB거리_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] dp;
	static int[][] houses;
	static int minValue;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		dp = new int[3];
		houses = new int[N][3];
		minValue = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			houses[i] = new int[] {R, G, B};
		}
		
		dp[0] = houses[0][0];
		dp[1] = houses[0][1];
		dp[2] = houses[0][2];
		
		for (int i = 1 ; i < N; i++) {
			int first = dp[0];
			int second = dp[1];
			int third = dp[2];
			dp[0] = Math.min(second, third)+houses[i][0];
			dp[1] = Math.min(first, third)+houses[i][1];
			dp[2] = Math.min(first, second)+houses[i][2];
		}
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(Math.min(dp[0], Math.min(dp[1], dp[2])));
	}

}
