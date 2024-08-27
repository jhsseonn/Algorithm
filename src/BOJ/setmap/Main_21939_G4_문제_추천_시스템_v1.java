package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_21939_G4_문제_추천_시스템_v1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Map<Integer, List<Integer>> qByLevel = new TreeMap<>();
	static Map<Integer, Integer> qByNumber = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			addQToRecommend(L, P);
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if (command.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				addQToRecommend(L, P);
			} else if (command.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				getRecommend(x);
			} else if (command.equals("solved")) {
				int p = Integer.parseInt(st.nextToken());
				removeRecommend(p);
			}
		}
	}
	
	private static void addQToRecommend(int P, int L) {
		qByNumber.put(P, L);
		List<Integer> values = qByLevel.getOrDefault(L, null);
		if (values==null) {
			values = new ArrayList<Integer>(Arrays.asList(P));
		} else {
			values.add(P);
		}
		qByLevel.put(L, values);
	}
	
	private static void getRecommend(int x) {
		// x=1 가장 어려운 문제 / x=-1 가장 쉬운 문제
		// key: 난이도 / value: 문제 번호
		// key로 정렬하고 value를 문제 번호로 정렬해서 가져온다
		
		
		if (x==1) {
			
		} else {
			
		}
	}
	
	private static void removeRecommend(int p) {
		int level = qByNumber.get(p);
		List<Integer> questions = qByLevel.get(level);
		questions.remove(p);
		qByNumber.remove(p);
		qByLevel.put(level, questions);
	}
	
}
