package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1828_S1_냉장고_장효선 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		Material[] m = new Material[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			m[i] = new Material(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(m);
		List<Material> materials = new ArrayList<>();
		materials.add(m[0]);
		
		for (int i = 1; i < N; i++) {
			if (materials.get(materials.size()-1).y<m[i].x) {
				materials.add(m[i]);
			}
		}
		
		System.out.println(materials.size());
	}
	
	static class Material implements Comparable<Material> {
		int x;
		int y;
		
		public Material(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Material o) {
			return this.y != o.y ? this.y-o.y : this.x-o.x;
		}
	}

}
