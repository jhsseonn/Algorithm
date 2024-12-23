package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1743_S1_Avoid_The_Lakes {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int maxCnt;
    static boolean[][] visited;
    static Deque<Pos> deque;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        maxCnt = Integer.MIN_VALUE;
        List<Pos> lakes = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
            lakes.add(new Pos(r, c));
        }

        for (Pos pos : lakes) {
            maxCnt = Math.max(maxCnt, bfs(pos));
        }

        System.out.println(maxCnt);
    }

    private static int bfs(Pos pos) {
        deque = new ArrayDeque<>();
        deque.offer(pos);
        visited[pos.r][pos.c] = true;
        int cnt = 1;

        while (!deque.isEmpty()) {
            Pos now = deque.poll();
            int r = now.r;
            int c = now.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    cnt++;
                    deque.offer(new Pos(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }

        return cnt;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
