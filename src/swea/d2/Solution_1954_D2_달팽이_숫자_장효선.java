package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1954_D2_달팽이_숫자_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int cnt = 1;
    static int[][] snail;
    static int[] dr = { 0, 1, 0, -1};
    static int[] dc = { 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            snail = new int[N][N];
            cnt = 1;

            snail[0][0] = 1;
            dfs(0, 0, 0);

            sb.append("#").append(test_case).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(snail[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int y, int idx) {
        if (cnt==N*N) {
            snail[x][y]=N*N;
            return;
        }
        snail[x][y]=cnt++;
        int nr = x+dr[idx];
        int nc = y+dc[idx];
        if (nr<0 || nr>=N || nc<0 || nc>=N || snail[nr][nc]!=0) {
            nr = nr-dr[idx];
            nc = nc-dc[idx];
            idx = (idx+1)%4;
            cnt--;
        }
        dfs(nr, nc, idx);
    }
}
