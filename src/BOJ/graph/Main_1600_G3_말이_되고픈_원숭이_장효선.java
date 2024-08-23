package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리: 64,568kb / 시간: 588ms
 */
public class Main_1600_G3_말이_되고픈_원숭이_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] travel;
    static boolean[][][] visited;
    static int W, H, K;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] hdr = {-2, -2, -1, -1, 2, 2, 1, 1};
    static int[] hdc = {-1, 1, -2, 2, -1, 1, -2, 2};

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        travel = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                travel[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < K + 1; k++) {
                    visited[i][j][k] = false;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Map> q = new ArrayDeque<>();

        // 시작 노드 넣고 방문처리
        q.offer(new Map(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Map node = q.poll();
            int r = node.r;
            int c = node.c;
            int move = node.move;
            int horseMove = node.horseMove;

            if (r == H - 1 && c == W - 1) {
                return move + horseMove;
            }

            // k<K인 경우 말의 이동으로 이동 시도
            // 아닌 경우 무조건 원숭이 이동
            if (horseMove < K) {
                for (int i = 0; i < 8; i++) {
                    int hnr = r + hdr[i];
                    int hnc = c + hdc[i];

                    if (hnr <= -1 || hnr >= H || hnc <= -1 || hnc >= W) continue;
                    if (travel[hnr][hnc] == 1 || visited[hnr][hnc][horseMove + 1]) continue;

                    visited[hnr][hnc][horseMove + 1] = true;
                    q.offer(new Map(hnr, hnc, move, horseMove + 1));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr <= -1 || nr >= H || nc <= -1 || nc >= W) continue;
                if (travel[nr][nc] == 1 || visited[nr][nc][horseMove]) continue;

                visited[nr][nc][horseMove] = true;
                q.offer(new Map(nr, nc, move + 1, horseMove));
            }

        }

        return -1;
    }

    private static class Map {
        int r;
        int c;
        int move;
        int horseMove;

        public Map(int r, int c, int move, int horseMove) {
            this.r = r;
            this.c = c;
            this.move = move;
            this.horseMove = horseMove;
        }
    }

}
