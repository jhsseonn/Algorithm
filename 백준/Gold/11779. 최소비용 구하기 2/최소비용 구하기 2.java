import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<Node>[] adjList;
    static int[] path;
    static long[] minDistance;
    static long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        minDistance = new long[N+1];
        path = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
            minDistance[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(from, to, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        sb.append(getMinDistance(start, end)).append("\n");
        // 경로 역추적
        Stack<Integer> s = new Stack<>();
        s.push(end);
        int cnt = 0;
        while(path[end]!=0) {
            cnt++;
            s.push(path[end]);
            end = path[end];
        }
        sb.append(cnt+1).append("\n");
        while(!s.isEmpty()) {
            sb.append(s.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static long getMinDistance(int start, int end) {
        minDistance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        pq.offer(new Node(0, start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.to] < cur.w) continue;

            for (Node e:adjList[cur.to]) {
                if (minDistance[e.to] <= minDistance[cur.to]+e.w) continue;
                minDistance[e.to] = minDistance[cur.to]+e.w;
                path[e.to] = cur.to;
                pq.offer(new Node(cur.to, e.to, minDistance[e.to]));
            }
        }

        return minDistance[end]!=INF ? minDistance[end] : -1;
    }

    private static class Node {
        int from, to;
        long w;

        public Node(final int from, final int to, final long w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
}
