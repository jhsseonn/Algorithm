package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 14,324kb / 104ms
 *
 */
public class Main_17069_G4_파이프_옮기기_2_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] arr;
	static long[][][] pipe;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		pipe = new long[N][N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pipe[0][1][2] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (arr[i][j]==1) continue;
				if(i == 0 && j == 1) continue;
				// 대각선에서 온 거: 1
				if (i>0 && j>0 && arr[i-1][j-1]==0 && arr[i-1][j]==0 && arr[i][j-1]==0) {
					pipe[i][j][1] = pipe[i-1][j-1][0] + pipe[i-1][j-1][1] + pipe[i-1][j-1][2];
				}
				// 위에서 온 거: 0
				if (i>0) {
					pipe[i][j][0] = pipe[i-1][j][0] + pipe[i-1][j][1]; 					
				}
				// 옆에서 온 거: 2
				if (j>0) {
					pipe[i][j][2] = pipe[i][j-1][1]+pipe[i][j-1][2];					
				}
				
			}
		}
		
		System.out.println(pipe[N-1][N-1][0]+pipe[N-1][N-1][1]+pipe[N-1][N-1][2]);
	}
}
