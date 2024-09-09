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
    static int N, M, X, Y, Z, minDisY, minDis;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        minDisY = minDis = Integer.MAX_VALUE;

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        int disXY = getMinDis(X, Y, true);
        int disYZ = getMinDis(Y, Z, true);
        int ans1 = 0;
        if (disXY!=-1 && disYZ!=-1) {
            ans1 = disXY+disYZ;
        } else {
            ans1 = -1;
        }
        int ans2 = getMinDis(X, Z, false);

        System.out.println(ans1 + " " + ans2);
    }

    private static int getMinDis(int start, int end, boolean containsY) {
        int[] minDis = new int[N + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(minDis, INF);
        minDis[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDis[cur.v] < cur.w) continue;

            for (Node e : adjList[cur.v]) {
                if (!containsY && e.v == Y) continue;
                if (minDis[e.v] < cur.w + e.w) continue;
                minDis[e.v] = cur.w + e.w;
                pq.offer(new Node(e.v, minDis[e.v]));
            }
        }

        return minDis[end] != INF ? minDis[end] : -1;
    }

    private static class Node {
        int v, w;

        public Node(final int v, final int w) {
            this.v = v;
            this.w = w;
        }
    }
}
