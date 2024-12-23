package ssafy12;

import java.util.Arrays;
import java.util.PriorityQueue;
import ssafy12.Dijkstra_init.Node;

public class Dijkstra_Array {

    static int[][] minDistance;
    static int[][] maze = {
            {0, 0, 0, 0},
            {-1, -1, 0, -1},
            {0, 0, 0, 0},
            {0, -1, -1, 0},
            {0, 0, 0, 0}
    };
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        minDistance = new int[5][4];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(minDistance[i], Integer.MAX_VALUE);
        }
        Node from = new Node(0, 0, 0);
        dijkstra(from);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (minDistance[i][j] == Integer.MAX_VALUE) {
                    System.out.print("X ");
                } else {
                    System.out.print(minDistance[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    private static void dijkstra(Node from) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.offer(from);
        minDistance[from.r][from.c] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.r][cur.c] < cur.w) continue;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr<0 || nr>=5 || nc<0 || nc>=4) continue;
                if (maze[nr][nc]==-1) continue;

                if (minDistance[nr][nc]<=cur.w+1) continue;
                minDistance[nr][nc] = cur.w+1;
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
