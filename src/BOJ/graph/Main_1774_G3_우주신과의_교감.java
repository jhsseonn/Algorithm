package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1774_G3_우주신과의_교감 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] nodes;
	static List<Node> adjList;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new int[N+1][2];
		adjList = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i][0] = Integer.parseInt(st.nextToken());
			nodes[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (union(a, b)) {
				double distance = Math.sqrt(Math.pow(nodes[a][0]-nodes[b][0], 2)+Math.pow(nodes[a][1]-nodes[b][1], 2));
				adjList.add(new Node(nodes[a], nodes[b], distance));
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = i+1; j < N+1; j++) {

			}
		}
	}

	private static void make() {
		parents = new int[N+1];
		Arrays.fill(parents, -1);
	}

	private static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}

	private static class Node implements Comparable<Node> {
		int[] from;
		int[] to;
		double distance;

		public Node(final int[] from, final int[] to, final double distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		@Override
		public int compareTo(final Node o) {
			if (this.distance > o.distance) return 1;
			else if (this.distance < o.distance) return -1;
			else return 0;
		}
	}

}
