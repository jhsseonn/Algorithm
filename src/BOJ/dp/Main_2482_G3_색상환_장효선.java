package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 18,152kb / 120ms
 *
 */
public class Main_2482_G3_색상환_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int MOD = (int) 1e9+3;
		int[][] dp = new int[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i-2][j-1]+dp[i-1][j])%MOD;
			}
		}
		
		System.out.println((dp[N-3][K-1]+dp[N-1][K])%MOD);
	}
}
