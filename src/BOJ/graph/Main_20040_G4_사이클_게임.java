package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 244,676kb / 648ms
 */
public class Main_20040_G4_사이클_게임 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] parents;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to);
        }

        make();

        int cnt = 0;
        int ans = 0;
        for (Edge now : edges) {
            if (union(now.from, now.to)) {
                cnt++;
            } else {
                ans = ++cnt;
                break;
            }
        }

        System.out.println(ans);
    }

    private static void make() {
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) {
            return a;
        } else {
            return parents[a] = findSet(parents[a]);
        }
    }

    private static boolean union(int a, int b) {
        int parentA = findSet(a);
        int parentB = findSet(b);
        if (parentA == parentB) {
            return false;
        }

//        parents[parentB] += parents[parentA];
        parents[parentA] = parentB;
        return true;
    }

    private static class Edge {
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
