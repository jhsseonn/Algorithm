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
	static int result;
	static int D, W, K, filmNum;
	static int[] subSetIdx, isChecked;
	static int[][] board, boardCopy;
	static boolean isValid;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			result = Integer.MAX_VALUE;
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[D][W];
			boardCopy = new int[D][W];
			subSetIdx = new int[D];
			isChecked = new int[D];
			isValid = false;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				boardCopy[i] = board[i].clone();
			}
			
			Arrays.fill(isChecked, -1);
			getSubset(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void getSubset(int depth, int len) {
		if (len>=result) return;
		if (depth==D) {
//			System.out.println(Arrays.toString(subset));
			for (int i = 0; i < isChecked.length; i++) {
				if (isChecked[i]!=-1) changeFilm(i, isChecked[i]);
			}
			if (checkFilm()) result = len;
//			dfs(0);
			return;
		}
		
		//A
		isChecked[depth] = 0;
		getSubset(depth+1, len+1);
		
		//B
		isChecked[depth] = 1;
		getSubset(depth+1, len+1);
		
		//넣지 않는 경우
		isChecked[depth] = -1;
		getSubset(depth+1, len);
	}
	
	private static void changeFilm(int filmIdx, int value) {
		for (int i = 0; i < W; i++) {
			boardCopy[filmIdx][i] = value;
		}
	}
	
	private static boolean checkFilm() {
		int cnt = 1;
		for (int i = 0; i < W; i++) {
			boolean pass = false;
			
			for (int j = 0; j < D-1; j++) {
				if (boardCopy[j][i]==boardCopy[j+1][i]) cnt+=1;
				else cnt=1;
			}
			if (cnt>=K) pass=true;
			if (pass==true) continue;
			else return false;
		}
		return true;
	}
}
