package BOJ.exhaustiveSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2583_S1_영역_구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M, N, K, ans;
    static Frozen[] areas;
    static int[][] arr;
    static List<Integer> seperated;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        areas = new Frozen[K];
        arr = new int[M][N];
        seperated = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            areas[i] = new Frozen(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (Frozen farea : areas) {
            int startR = farea.y1;
            int startC = farea.x1;
            int endR = farea.y2-1;
            int endC = farea.x2-1;

            for (int i = startR; i <= endR; i++) {
                for (int j = startC; j <= endC; j++) {
                    arr[i][j] = 1; // 얼어붙은 영역 표시
                }
            }
        }

        bfs();

        int[] seperatedArr = new int[seperated.size()];
        for (int i = 0; i < seperatedArr.length; i++) {
            seperatedArr[i] = seperated.get(i);
        }

        Arrays.sort(seperatedArr);

        System.out.println(seperatedArr.length);
        for (int i = 0; i < seperatedArr.length; i++) {
            System.out.print(seperatedArr[i]+" ");
        }
    }

    private static void bfs() {
        Deque<Area> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[M][N];
        while (true) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j]==0 && !visited[i][j]) {
                        dq.offer(new Area(i, j));
                        visited[i][j] = true;
                        ans+=1;
                        break;
                    }
                }
                if (!dq.isEmpty()) break;
            }

            if (dq.isEmpty()) break;

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while(!dq.isEmpty()) {
                Area cur = dq.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r+dr[i];
                    int nc = cur.c+dc[i];

                    // 지도 범위 밖으로 나갈 경우 or 이미 방문한 경우 탐색하지 않음
                    if (nr<0 || nr>=M || nc<0 || nc>=N || visited[nr][nc]) continue;

                    // 얼어붙은 영역일 경우 탐색하지 않음
                    if (arr[nr][nc]==1) continue;



                    // 방문 처리 및 영역 넓이 계산, 다음에 주위 탐색
                    visited[nr][nc] = true;

                    ans+=1;
                    dq.offer(new Area(nr, nc));
                }
            }
            seperated.add(ans);
            ans = 0;
        }
    }

    static class Frozen {
        int x1, y1, x2, y2;

        public Frozen(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static class Area {
        int r, c;

        public Area(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
