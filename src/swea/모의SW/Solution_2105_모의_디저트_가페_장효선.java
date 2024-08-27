package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_모의_디저트_가페_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, startR, startC, ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] cdr = {1, 1, -1, -1};
    static int[] cdc = {1, -1, -1, 1};
    static int[][] cafe;
    static boolean[][] visited;
    static boolean[][] checked;
    static boolean[] isNotValid;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            cafe = new int[N][N];
            checked = new boolean[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    startR = i;
                    startC = j;
                    visited = new boolean[N][N];
                    isNotValid = new boolean[101];
                    visited[startR][startC] = true;
                    isNotValid[cafe[i][j]] = true;
                    findCafe(i, j, 0, 0);
                }
            }

            if (ans < 3) ans = -1;

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void findCafe(int r, int c, int enterDir, int dessert) {
        if (startR==r-1 && startC==c+1) {  // 시작점의 대각선 아래에 도달하면 탐색을 멈춘다
            ans = Math.max(ans, dessert+1);
            return;
        }

        for (int i = enterDir; i < 4; i++) {
            int nr = r+cdr[i];
            int nc = c+cdc[i];

            if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
            if (visited[nr][nc]) continue;

            if (isNotValid[cafe[nr][nc]]) continue;

            visited[nr][nc] = true;
            isNotValid[cafe[nr][nc]] = true;
            findCafe(nr, nc, i, dessert+1);
            visited[nr][nc] = false;
            isNotValid[cafe[nr][nc]] = false;
        }
    }
}
