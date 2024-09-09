package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 27,404kb / 189ms
 *
 */
public class Solution_5658_모의_보물상자_비밀번호_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, K, ans;
	static Set<String> keys;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int len = N/4;
			keys = new HashSet<>();
			
			// 비밀번호 배열 받기
			String str = br.readLine();
			str+=str.substring(0, len-1);
		
			sb.append("#").append(test_case).append(" ").append(getKNum(str, len)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int getKNum(String str, int len) {
		for (int i = 0; i < N; i++) {
			keys.add(str.substring(i, i+len));
		}
		
		String[] keyArray = keys.stream().toArray(String[]::new);
		Arrays.sort(keyArray, Collections.reverseOrder());
		
		return Integer.parseInt(keyArray[K-1], 16);
	}

}
