package BOJ.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 14,408kb / 112ms
 */
public class Main_2665_G4_미로만들기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] rooms;
    static int[][] minDistance;
    static boolean[][] visited;
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        rooms = new char[N][N];
        minDistance = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            rooms[i] = br.readLine().toCharArray();
            Arrays.fill(minDistance[i], Integer.MAX_VALUE);
        }

        dijkstra(new Node(0, 0, 0));

        System.out.println(minDistance[N-1][N-1]);
    }

    private static void dijkstra(Node from) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.offer(from);
        minDistance[from.r][from.c] = 0;
        visited[from.r][from.c] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.r==N-1 && cur.c==N-1) return;

            if (minDistance[cur.r][cur.c] < cur.w) continue;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                int w = rooms[nr][nc]=='0' ? 1 : 0;
                if (minDistance[nr][nc]<=cur.w+w) continue;
                minDistance[nr][nc] = cur.w+w;
                pq.offer(new Node(nr, nc, minDistance[nr][nc]));
            }
        }
    }

    static class Node {
        int r, c, w;

        public Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}
