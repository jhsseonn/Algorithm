package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 62,136kb / 540ms
 */
public class Main_1238_G3_파티 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X;
    static List<Node>[] nodes;
    static int[] minDisToGo;
    static int[] minDisToCome;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];
        minDisToGo = new int[N + 1];
        minDisToCome = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[from].add(new Node(to, weight));
        }

        getMinDistance(X);
        int[] res = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            res[i] = getMinDisToCome(i);
            res[i] += minDisToGo[i];
        }

        Arrays.sort(res);
        System.out.println(res[N]);
    }

    private static int getMinDisToCome(int start) {
        int INF = Integer.MAX_VALUE;
        Arrays.fill(minDisToCome, INF);
        minDisToCome[0] = 0;
        minDisToCome[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDisToCome[cur.v] < cur.w) continue;

            for (Node e : nodes[cur.v]) {
                if (minDisToCome[e.v] <= cur.w + e.w) continue;
                minDisToCome[e.v] = cur.w + e.w;
                pq.offer(new Node(e.v, minDisToCome[e.v]));
            }
        }

        return minDisToCome[X];
    }

    private static void getMinDistance(int start) {
        int INF = Integer.MAX_VALUE;
        Arrays.fill(minDisToGo, INF);
        minDisToGo[0] = 0;
        minDisToGo[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDisToGo[cur.v] < cur.w) continue;

            for (Node e : nodes[cur.v]) {
                if (minDisToGo[e.v] <= cur.w + e.w) continue;
                minDisToGo[e.v] = cur.w + e.w;
                pq.offer(new Node(e.v, minDisToGo[e.v]));
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
