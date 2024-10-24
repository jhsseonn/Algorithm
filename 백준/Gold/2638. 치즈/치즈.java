import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static Pos[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new Pos[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int isCheese = Integer.parseInt(st.nextToken());
                if (isCheese==0) {
                    arr[i][j] = new Pos(i, j, 0, false, false);
                } else {
                    arr[i][j] = new Pos(i, j, 0, true, false);
                }
            }
        }

        int time = 0;
        while (true) {
            time++;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j].edge!=0) arr[i][j].edge=0;
                }
            }
            findInside();
            int cntCheese = cntCheese();
            if (cntCheese == 0) break; // 모든 치즈가 녹은 경우
        }

        // 치즈가 모두 녹아 없어지는데 걸리는 시간 구하기
        System.out.println(time);
    }

    // 치즈 안쪽 찾기(BFS)
    private static void findInside() {
        boolean[][] visited = new boolean[R][C];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(arr[0][0]); // 왼쪽 최상단 칸을 시작
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            for (int i = 0; i < 4; i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                if (arr[nr][nc].isCheese && !arr[nr][nc].melted) {  // 치즈가 있으면 치즈에 edge 카운트 올려주기
                    arr[nr][nc].edge+=1;
                } else {
                    visited[nr][nc] = true;
                    q.offer(arr[nr][nc]);
                }
            }
        }
    }

    private static int cntCheese() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j].edge>=2 && !arr[i][j].melted) {
                    arr[i][j].melted = true;
                    arr[i][j].edge = 0;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j].isCheese && !arr[i][j].melted) cnt++;
            }
        }

        return cnt;
    }

    static class Pos{
        int r, c, edge;
        boolean isCheese;
        boolean melted;

        public Pos(int r, int c, int edge, boolean isCheese, boolean melted) {
            this.r = r;
            this.c = c;
            this.edge = edge;
            this.isCheese = isCheese;
            this.melted = melted;
        }
    }
}
