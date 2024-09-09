package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 21,320kb / 시간: 117ms
 */
public class Solution_1949_모의_등산로_조성_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, K, maxLength;
    static boolean cut;
    static List<int[]> starts;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            visited = new boolean[N][N];
            starts = new ArrayList<>();
            maxLength = Integer.MIN_VALUE;
            int max = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, arr[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j]==max) starts.add(new int[] {i, j});
                }
            }

            for (int k = 1; k <= K; k++) {
                for (int[] start:starts) {
                    cut = false;
                    visited[start[0]][start[1]] = true;
                    dfs(start[0], start[1], 1, k);
                    visited[start[0]][start[1]] = false;
                }
            }

            sb.append("#").append(test_case).append(" ").append(maxLength).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int r, int c, int cnt, int k) {
        if (cnt>maxLength) {
            maxLength = cnt;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];

            if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
            if (visited[nr][nc]) continue;

            if (arr[nr][nc]<arr[r][c]) {
                visited[nr][nc]=true;
                dfs(nr, nc, cnt+1, k);
                visited[nr][nc]=false;
            } else if (!cut && (arr[nr][nc]-k < arr[r][c])) {
                // 지금까지 깎은 봉우리가 없는 경우 1 ~ K만큼 깎는다
                cut=true;
                visited[nr][nc] = true;
                arr[nr][nc]-=k;
                dfs(nr, nc, cnt+1, k);
                visited[nr][nc] = false;
                cut=false;
                arr[nr][nc]+=k;
            }
        }
    }
}
