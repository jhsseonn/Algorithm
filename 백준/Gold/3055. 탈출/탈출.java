import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int R, C;
    static char[][] arr;
    static int[][] res;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static Deque<Status> waterDq;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        res = new int[R][C];
        int[] start = new int[2];
        waterDq = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'S') start = new int[]{i, j};  // 고슴도치 시작 위치 저장
                if (arr[i][j] == '*') {
                    waterDq.offer(new Status(i, j, 0));  // 홍수 시작 위치 저장
                }
                if (arr[i][j] == '.') res[i][j] = Integer.MAX_VALUE;
            }
        }

        getFlood();

        int time = getMinTime(start);

        if (time == -1) System.out.println("KAKTUS");
        else System.out.println(time);
    }

    private static int getMinTime(int[] start) {
        boolean[][] visited = new boolean[R][C];
        Deque<Status> dq = new ArrayDeque<>();
        dq.offer(new Status(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;
        int[][] ans = new int[R][C];
        int endR = -1, endC = -1;

        while (!dq.isEmpty()) {
            Status cur = dq.poll();
            int r = cur.r;
            int c = cur.c;
            int depth = cur.depth;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 밖, 이미 방문한 경우, 돌이 있는 경우 지나갈 수 없음
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || arr[nr][nc] == 'X') continue;

                if (res[nr][nc] <= depth + 1 && res[nr][nc] != 0) continue; // 고슴도치가 지나가기 전 물이 지나간 경우 지나갈 수 없음
                if (res[nr][nc] == 0 && arr[nr][nc] == '*') continue;  // 물의 시작점은 지나갈 수 없다
                if (arr[nr][nc] == 'D') {
                    endR = nr;
                    endC = nc;
                }

                visited[nr][nc] = true;
                ans[nr][nc] = depth + 1;  // 최단경로 갱신
                dq.offer(new Status(nr, nc, depth + 1));
            }
        }

        if (endR == -1 && endC == -1) return -1;
        else return ans[endR][endC];
    }

    private static void getFlood() {
        boolean[][] visited = new boolean[R][C];
        for (Status s : waterDq) {
            visited[s.r][s.c] = true;
        }

        while (!waterDq.isEmpty()) {
            Status cur = waterDq.poll();
            int r = cur.r;
            int c = cur.c;
            int depth = cur.depth;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || arr[nr][nc] == 'X' || arr[nr][nc] == 'D') continue;

                visited[nr][nc] = true;
                res[nr][nc] = depth + 1;
                waterDq.offer(new Status(nr, nc, depth + 1));
            }
        }
    }

    static class Status {
        int r, c, depth;

        public Status(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
}
