package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 125,028kb / 시간: 793ms
 */
public class Solution_1251_D4_하나로_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static double E;
	static Island[] islands;
	static int[] parents;
	static List<Edge> edges;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			N = Integer.parseInt(br.readLine());  // 섬의 개수


			StringTokenizer xs = new StringTokenizer(br.readLine());  // 섬들의 x좌표
			StringTokenizer ys = new StringTokenizer(br.readLine());  // 섬들의 y좌표
			islands = new Island[N];  // 섬들의 좌표 위치 정보를 가지고 있는 배열
			E = Double.parseDouble(br.readLine());  // 환경 부담 세율

			for (int i = 0; i < N; i++) {
				double x = Double.parseDouble(xs.nextToken());
				double y = Double.parseDouble(ys.nextToken());
				islands[i] = new Island(x, y);
			}

			edges = new ArrayList<>();
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					double xd = Math.pow(islands[i].x-islands[j].x, 2);
					double yd = Math.pow(islands[i].y-islands[j].y, 2);
					double L = (xd+yd)*E;  // 간선의 길이의 제곱에 E를 곱한 것만큼이 가중치이므로
					edges.add(new Edge(i, j, L));
				}
			}

			Edge[] edgesArray = new Edge[edges.size()];
			for (int i = 0; i < edges.size(); i++) {
				edgesArray[i] = edges.get(i);
			}

			Arrays.sort(edgesArray);
			
			sb.append("#").append(test_case).append(" ").append((long)getBridge(edgesArray)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static double getBridge(Edge[] edges) {
		make();
		
		double result = 0;
		int cnt = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				result+=edge.L;
				cnt+=1;
				if (cnt==N-1) break;
			}
		}
		return result+0.5;
	}
	
	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = -1;
		}
	}
	
	private static int findSet(int a) {
		if (parents[a]<0) return a;
		return parents[a]=findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		
//		parents[aRoot]+=parents[bRoot];
		parents[bRoot]= aRoot;
		return true;
	}
	
	private static class Island {
		double x, y;

		public Island(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Edge implements Comparable<Edge> {
		int start;
		int end;
		double L;

		public Edge(int start, int end, double L) {
			this.start = start;
			this.end = end;
			this.L = L;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.L, o.L);
		}
	}

}
