package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 메모리: 42,220kb / 시간: 223ms
 */
public class Solution_1868_D4_파핑파핑_지뢰찾기_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, result;
    static char[][] minesweeper;
    static boolean[][] visited;
    static BoardInfo start;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            N = Integer.parseInt(br.readLine());
            minesweeper = new char[N][N];
            visited = new boolean[N][N];
            start = null;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    minesweeper[i][j] = str.charAt(j);
                }
            }
            result = getClickCount();

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int getClickCount() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (checkMine(i, j) == 0 && minesweeper[i][j] != '*' && !visited[i][j]) {
                    cnt += 1;
                    start = new BoardInfo(i, j, 0);
                    bfs();
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (minesweeper[i][j] == '.' && !visited[i][j]) {
                    minesweeper[i][j] = (char) checkMine(i, j);
                    visited[i][j] = true;
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    private static void bfs() {
        Deque<BoardInfo> q = new ArrayDeque<>();
        visited[start.r][start.c] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            BoardInfo cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int cnt = checkMine(r, c);
            minesweeper[r][c] = (char) cnt;

            if (cnt != 0) continue;

            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 밖이거나 방문한 칸이면 넘어감
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                // 지뢰가 있는 칸이면 넘어감
                if (minesweeper[nr][nc] == '*') continue;

                // 방문처리
                visited[nr][nc] = true;
                q.offer(new BoardInfo(nr, nc, 0));
            }
        }
    }

    private static int checkMine(int r, int c) {
        int mineNum = 0;

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 범위 밖이면 넘어감
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            // 해당 칸에 지뢰가 있으면 지뢰 개수를 누적한다
            if (minesweeper[nr][nc] == '*') {
                mineNum += 1;
            }
        }

        return mineNum;
    }

    private static class BoardInfo {
        int r;
        int c;
        int mineNum;

        public BoardInfo(int r, int c, int mineNum) {
            this.r = r;
            this.c = c;
            this.mineNum = mineNum;
        }

        public void setMineNum(int mineNum) {
            this.mineNum = mineNum;
        }
    }
}
