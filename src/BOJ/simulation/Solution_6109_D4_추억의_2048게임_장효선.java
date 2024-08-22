package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_6109_D4_추억의_2048게임_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static String[] directions = {"up", "down", "left", "right"};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int[][] board;
    static int[][] ans;
    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String D = st.nextToken();
            board = new int[N][N];
            ans = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            moveTile(D);

            sb.append("#").append(test_case).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(ans[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    // 입력받은 방향으로 타일 밀기
    private static void moveTile(String D) {
        if (D.equals("up")) {
            for (int c = 0; c < N; c++) {
                dq = new ArrayDeque<>();
                for (int r = 0; r < N; r++) {
                    if (board[r][c] == 0) continue;
                    dq.offer(board[r][c]);
                }

                int rIdx = 0;
                while (!dq.isEmpty()) {
                    int num = dq.poll();
                    int num2 = dq.peek() != null ? dq.peek() : -1;
                    if (num == num2 && num2 != -1) {
                        ans[rIdx][c] = num + num2;
                        dq.poll();
                    } else {
                        ans[rIdx][c] = num;
                    }
                    rIdx++;
                }
                if (rIdx < N) {
                    while (rIdx < N) {
                        ans[rIdx][c] = 0;
                        rIdx++;
                    }
                }
            }
        }

        if (D.equals("down")) {
            for (int c = 0; c < N; c++) {
                dq = new ArrayDeque<>();
                for (int r = N - 1; r >= 0; r--) {
                    if (board[r][c] == 0) continue;
                    dq.offer(board[r][c]);
                }

                int rIdx = N - 1;
                while (!dq.isEmpty()) {
                    int num = dq.poll();
                    int num2 = dq.peek() != null ? dq.peek() : -1;
                    if (num == num2 && num2 != -1) {
                        ans[rIdx][c] = num + num2;
                        dq.poll();
                    } else {
                        ans[rIdx][c] = num;
                    }
                    rIdx--;
                }
                if (rIdx >= 0) {
                    while (rIdx >= 0) {
                        ans[rIdx][c] = 0;
                        rIdx--;
                    }
                }
            }
        }

        if (D.equals("left")) {
            for (int r = 0; r < N; r++) {
                dq = new ArrayDeque<>();
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) continue;
                    dq.offer(board[r][c]);
                }

                int cIdx = 0;
                while (!dq.isEmpty()) {
                    int num = dq.poll();
                    int num2 = dq.peek() != null ? dq.peek() : -1;
                    if (num == num2 && num2 != -1) {
                        ans[r][cIdx] = num + num2;
                        dq.poll();
                    } else {
                        ans[r][cIdx] = num;
                    }
                    cIdx++;
                }
                if (cIdx < N) {
                    while (cIdx < N) {
                        ans[r][cIdx] = 0;
                        cIdx++;
                    }
                }
            }
        }

        if (D.equals("right")) {
            for (int r = 0; r < N; r++) {
                dq = new ArrayDeque<>();
                for (int c = N - 1; c >= 0; c--) {
                    if (board[r][c] == 0) continue;
                    dq.offer(board[r][c]);
                }

                int cIdx = N - 1;
                while (!dq.isEmpty()) {
                    int num = dq.poll();
                    int num2 = dq.peek() != null ? dq.peek() : -1;
                    if (num == num2 && num2 != -1) {
                        ans[r][cIdx] = num + num2;
                        dq.poll();
                    } else {
                        ans[r][cIdx] = num;
                    }
                    cIdx--;
                }
                if (cIdx >= 0) {
                    while (cIdx >= 0) {
                        ans[r][cIdx] = 0;
                        cIdx--;
                    }
                }
            }
        }
    }
}
