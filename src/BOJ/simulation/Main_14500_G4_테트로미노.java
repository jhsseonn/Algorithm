package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 122,224kb / 584ms
 */
public class Main_14500_G4_테트로미노 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, ans;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Pos p = new Pos(i, j);
                visited[i][j] = true;
                getMax(0, p, arr[i][j]);
                visited[i][j] = false;
                getMaxTop(p);
            }
        }

        System.out.println(ans);
    }

    // 최댓값 칸마다 가능한 테트로미노 최댓값 구하기
    private static void getMax(int depth, Pos pos, int sum) {
        if (depth == 3) {
            ans = Math.max(ans, sum);
            return;
        }

        int x = pos.x;
        int y = pos.y;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            getMax(depth + 1, new Pos(nx, ny), sum + arr[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    // ㅗ 모양
    private static void getMaxTop(Pos pos) {
        int x = pos.x;
        int y = pos.y;

        // ㅗ
        if (x > 0 && y > 0 && y < M - 1) {
            ans = Math.max(ans, arr[x][y] + arr[x - 1][y] + arr[x][y - 1] + arr[x][y + 1]);
        }

        // ㅜ
        if (x < N - 1 && y > 0 && y < M - 1) {
            ans = Math.max(ans, arr[x][y] + arr[x + 1][y] + arr[x][y - 1] + arr[x][y + 1]);
        }

        // ㅓ
        if (x > 0 && x < N - 1 && y > 0) {
            ans = Math.max(ans, arr[x][y] + arr[x - 1][y] + arr[x + 1][y] + arr[x][y - 1]);
        }

        // ㅏ
        if (x > 0 && x < N - 1 && y < M - 1) {
            ans = Math.max(ans, arr[x][y] + arr[x - 1][y] + arr[x + 1][y] + arr[x][y + 1]);
        }
    }

    static class Pos {
        int x, y;

        public Pos(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

}
