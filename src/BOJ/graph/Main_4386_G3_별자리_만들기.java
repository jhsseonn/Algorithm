package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 14,796kb / 시간: 116ms
 *
 */
public class Main_4386_G3_별자리_만들기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, parents[];
	static double minDistance;
	static double[][] stars;
	static List<Edge> edges;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stars = new double[N][2];
		edges = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new double[] {Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())};
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				double[] from = stars[i];
				double[] to = stars[j];
				double weight = Math.sqrt(Math.pow(from[0]-to[0], 2)+Math.pow(from[1]-to[1], 2)); 
//				System.out.println(Arrays.toString(from)+" "+Arrays.toString(to)+" "+weight);
				edges.add(new Edge(i, j, weight));
			}
		}
		
		Collections.sort(edges);
		getMinDistance(edges);
		
		System.out.println(String.format("%.2f", minDistance));
	}
	
	private static void getMinDistance(List<Edge> edges) {
		make();
		
		int cnt = 0;
		for (Edge e : edges) {
			if (union(e.from, e.to)) {
				minDistance+=e.weight;
				if (++cnt==N-1) break;
			}
		}
	}
	
	private static void make() {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	private static int findSet(int a) {
		if (parents[a]<0) return a;
		return parents[a]=findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		parents[aRoot]=bRoot;
		return true;
	}
	
	private static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight > o.weight) return 1;
			else if (this.weight < o.weight) return -1;
			return 0;
		}
	}

}
