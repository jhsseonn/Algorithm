package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_모의SW_보호_필름_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int result = Integer.MAX_VALUE;
	static int D, W, K;
	static int[] subSetIdx;
	static int[][] board;
	static int[][] boardCopy;
	static boolean isValid;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[D][W];
			boardCopy = new int[D][W];
			subSetIdx = new int[D];
			isValid = false;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				boardCopy[i].clone();
			}
			
			// 최적해 구하기
			getMinResult();
			
			sb.append("#").append(test_case).append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void getMinResult() {
		
	}

	private static void getSubset(int depth, int len) {
		if (depth==D) {
			evaluate(Arrays.copyOfRange(subSetIdx, 0, len));
			return;
		}
		subSetIdx[len] = depth;
		getSubset(depth+1, len+1);
		getSubset(depth+1, len);
	}
	
	private static void evaluate(int[] subset) {
		if (subset.length==0) return;
		for (int i = 0; i < subset.length; i++) {
			for (int j = 0; j < W; j++) {
				boardCopy[i][j] = 0;
				dfs(0);
				boardCopy[i][j] = 1;
				dfs(0);
			}
		}
		
	}
	
	private static void dfs(int depth) {
		if (depth==W) {
			isValid = true;
			return;
		}
		for (int i = 0; i < D-2; i++) {
			if (boardCopy[i][depth]==boardCopy[i+1][depth] && boardCopy[i][depth]==boardCopy[i+2][depth]) {
				dfs(depth+1);
			} else return;
		}
	}
}
