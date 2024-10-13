import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, W, H, ans;
    static int[][] arr, copyArr;
    static int[] crystals;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            arr = new int[H][W];
            crystals = new int[N];
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            getCrystals(0);

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void getCrystals(int depth) {
        if (depth == N) {
            copyArr = new int[H][W];
            for (int i = 0; i < H; i++) {
                copyArr[i] = arr[i].clone();
            }

            dropCrystal();
            int cnt = getResult();
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 0; i < W; i++) {
            crystals[depth] = i;
            getCrystals(depth + 1);
        }
    }

    private static int getResult() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copyArr[i][j] != 0) cnt += 1;
            }
        }

        return cnt;
    }

    private static void dropCrystal() {
        for (int i = 0; i < N; i++) {
            int col = crystals[i];
            for (int j = 0; j < H; j++) {
                if (copyArr[j][col] == 0) continue;
                breakBricks(j, col);
                break;
            }

            // 중력 작용
            gravity();
        }
    }

    private static void breakBricks(int r, int c) {
        if (r < 0 || r >= H || c < 0 || c >= W || copyArr[r][c] == 0) {
            return;
        }

        int power = copyArr[r][c];
        copyArr[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < power; j++) {
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;

                breakBricks(nr, nc);
            }
        }
    }

    private static void gravity() {
        for (int i = 0; i < W; i++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int j = 0; j < H; j++) {
                if (copyArr[j][i] != 0) dq.offer(copyArr[j][i]);
            }

            for (int j = H - 1; j >= 0; j--) {
                if (!dq.isEmpty()) {
                    copyArr[j][i] = dq.pollLast();
                } else {
                    copyArr[j][i] = 0;
                }
            }
        }
    }
}
