package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리: 50,088kb / 시간: 592ms
 */
public class Main_1922_G4_네트워크_연결 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[] parents;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new Edge[M];
        int ans = 0, cnt = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);
        make();

        for (int i = 0; i < edges.length; i++) {
            Edge cur = edges[i];
            if (union(cur.from, cur.to)) {
                ans += cur.w;
                if (++cnt == N - 1) break;
            }
        }

        System.out.println(ans);
    }

    private static void make() {
        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = -1;
        }
    }

    private static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;

        parents[aRoot] += parents[bRoot];
        parents[aRoot] = bRoot;
        return true;
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, w;

        public Edge(final int from, final int to, final int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(final Edge o) {
            return this.w - o.w;
        }
    }
}
