import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int V, E, start;
	static List<Node>[] adjList;
	static int[] minDistance;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V+1];
		minDistance = new int[V+1];

		for (int i = 0; i < V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, weight));
		}

		getMinDistance(start);

		for (int i = 1; i < V+1; i++) {
			if (minDistance[i]==INF) sb.append("INF").append("\n");
			else sb.append(minDistance[i]).append("\n");
		}

		System.out.println(sb);
	}

	private static void getMinDistance(int start) {
		Arrays.fill(minDistance, INF);
		minDistance[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if (minDistance[cur.v] < cur.w) continue;

			for (Node e:adjList[cur.v]) {
				if (minDistance[e.v] <= cur.w+e.w) continue;
				minDistance[e.v] = cur.w+e.w;
				pq.offer(new Node(e.v, minDistance[e.v]));
			}
		}
	}
	
	private static class Node {
		int v, w;

		public Node(final int v, final int w) {
			this.v = v;
			this.w = w;
		}
	}
}
