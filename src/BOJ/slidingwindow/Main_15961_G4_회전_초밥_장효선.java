package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 170,850kb / 시간: 496ms
 *
 */
public class Main_15961_G4_회전_초밥_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, d, k, c;
	static int[] sushi;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N+k-1];
		
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		for (int i = N; i < N+k-1; i++) {  // 회전 고려
			sushi[i] = sushi[i-N];
		}
		
		System.out.println(getMaxValue());
	}
	
	private static int getMaxValue() {
		int maxValue = Integer.MIN_VALUE; // k개의 초밥 안에 
		int[] checked = new int[d+1];
		checked[c] = 1;
		
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if (checked[sushi[i]]==0) cnt+=1;
			checked[sushi[i]]+=1;
		}

		maxValue = cnt+1;
//		System.out.println(cnt);
		
		for (int i = 0; i < N-1; i++) {	
			checked[sushi[i]] -= 1;
			if (checked[sushi[i]]==0) cnt-=1; 

			if (checked[sushi[k+i]]==0) cnt+=1;
			checked[sushi[k+i]]+=1;
			
			maxValue = Math.max(maxValue, cnt+1);
//			System.out.println(cnt);
		}
		
		return maxValue;
	}

}
