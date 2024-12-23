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

    static List<Node>[] adjList;
    static int N, M, R, total;
    static int[] minDistance, items;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 노드 개수
        M = Integer.parseInt(st.nextToken());  // 수색 범위
        R = Integer.parseInt(st.nextToken());  // 길의 개수
        adjList = new ArrayList[N+1];
        minDistance = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();  // 인접리스트 초기화
        }

        // 각 구역의 아이템 수 입력
        items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 가중치 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));  // 양방향
        }

        total = Integer.MIN_VALUE;
        for (int i = 1; i < N+1; i++) {
            dijkstra(i);
        }

        System.out.println(total);
    }

    private static void dijkstra(int from) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        pq.offer(new Node(from, 0));
        minDistance[from] = 0;

        boolean[] visited = new boolean[N+1];
        visited[from] = true;
        int sum = items[from];

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.n] < cur.w) continue;

            for(Node next:adjList[cur.n]) {
                if (minDistance[next.n]<=minDistance[cur.n]+next.w) continue;
                minDistance[next.n] = minDistance[cur.n]+next.w;

                if (minDistance[next.n] > M) continue;  // 수색 범위를 넘어서면 안된다 -> 경로 길이가 수색 범위보다 크면 안됨
                if (!visited[next.n]) {
                    visited[next.n] = true;
                    sum += items[next.n];
                }
                pq.offer(new Node(next.n, minDistance[next.n]));
            }
        }

        total = Math.max(total, sum);
    }

    static class Node {
        int n, w;

        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
