package ssafy12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Node>[] adjList;
    static long[] minDistance;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        minDistance = new long[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
            minDistance[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, w));
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        getMinDistance(from);

        System.out.println(minDistance[to]);
    }

    private static void getMinDistance(int from) {
        minDistance[from] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        pq.offer(new Node(from, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.next] < cur.w) continue;

            for (Node now : adjList[cur.next]) {
                if (minDistance[now.next] <= minDistance[cur.next] + now.w) continue;
                minDistance[now.next] = minDistance[cur.next] + now.w;
                pq.offer(new Node(now.next, minDistance[now.next]));
            }
        }
    }

    static class Node {
        int next;
        long w;

        public Node(final int next, final long w) {
            this.next = next;
            this.w = w;
        }
    }
}
