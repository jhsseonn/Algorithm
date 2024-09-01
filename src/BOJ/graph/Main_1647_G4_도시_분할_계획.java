package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리: 350,180kb / 시간: 2452ms
 */
public class Main_1647_G4_도시_분할_계획 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, maxEdge;
    static Edge[] edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        int ans = 0, cnt = 0;
        maxEdge = Integer.MIN_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);
        make();

        for (Edge e:edges) {
            if (union(e.from, e.to)){
                maxEdge = Math.max(maxEdge, e.weight);
                ans+=e.weight;
                if (++cnt==N-1) break;
            }
        }

        System.out.println(ans-maxEdge);
    }

    private static void make() {
        parents = new int[N+1];
        Arrays.fill(parents, -1);
    }

    private static int findSet(int a) {
        if (parents[a]<0) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot==bRoot) return false;

        parents[aRoot]+=parents[bRoot];
        parents[aRoot]=bRoot;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(final int from, final int to, final int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(final Edge o) {
            return this.weight-o.weight;
        }
    }
}
