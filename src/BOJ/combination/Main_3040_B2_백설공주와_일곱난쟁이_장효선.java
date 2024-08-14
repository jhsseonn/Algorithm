package com.ssafy.ws.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2_3040_백설공주와_일곱난쟁이_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] hatNums = new int[9];
	static int[] combination = new int[7];

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			hatNums[i] = Integer.parseInt(st.nextToken());
		}

		combination(0, 0);
	}

	private static void combination(int depth, int start) {
		if (depth == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += combination[i];
			}
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					System.out.println(combination[i]);
				}
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			combination[depth] = hatNums[i];
			combination(depth + 1, i + 1);
		}
	}

}
