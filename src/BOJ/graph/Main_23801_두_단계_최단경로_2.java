package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_23801_두_단계_최단경로_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X, Z, P;
    static List<Node>[] adjList;
    static int[] Y;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, w));
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        P = Integer.parseInt(br.readLine());
        Y = new int[P];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < P; i++) {
            int end = Integer.parseInt(st.nextToken());
            int XY = getMinDistance(X, end);
            int YZ = getMinDistance(end, Z);
            if (XY!=-1 && YZ!=-1) {
                Y[i] = XY+YZ;
            } else {
                Y[i] = -1;
            }
        }

        Arrays.sort(Y);

        for (int ans:Y) {
            if (ans!=-1) {
                System.out.println(ans);
                break;
            }
        }
    }

    private static int getMinDistance(int start, int end) {
        int[] minDistance = new int[N+1];
        boolean[] visited = new boolean[N+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(minDistance, INF);
        minDistance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (minDistance[e.to] < cur.w + e.w) continue;
                minDistance[e.to] = cur.w+e.w;
                pq.offer(new Node(e.to, minDistance[e.to]));
            }
        }

        return (minDistance[end]!=INF) ? minDistance[end] : -1;
    }

    private static class Node {
        int to, w;

        public Node(final int to, final int w) {
            this.to = to;
            this.w = w;
        }
    }
}
