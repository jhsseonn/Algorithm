import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        Position start = null;
        int totalTime = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 처음 아기상어의 위치 저장
                if (arr[i][j]==9) {
                    start = new Position(i, j);
                    arr[i][j] = 0;
                }
            }
        }

        Position cur = start;
        int eaten = 0;
        int sharkSize = 2;
        while(true) {
            FishInfo fish = bfs(cur, sharkSize);
            if (fish==null) break;

            totalTime+= fish.time;
            cur = new Position(fish.r, fish.c);
            arr[fish.r][fish.c] = 0;

            eaten++;
            if (eaten==sharkSize) {
                sharkSize += 1;
                eaten = 0;
            }
        }

        System.out.println(totalTime);
    }

    private static FishInfo bfs(Position pos, int sharkSize) {
        boolean[][] visited = new boolean[N][N];
        Deque<Position> dq = new ArrayDeque<>();
        dq.offer(pos);
        visited[pos.r][pos.c] = true;
        int time = 0;
        List<FishInfo> nearestFish = new ArrayList<>();

        while(!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Position curPos = dq.poll();
                int r = curPos.r;
                int c = curPos.c;

                for (int j = 0; j < 4; j++) {
                    int nr = r+dr[j];
                    int nc = c+dc[j];

                    // 범위 밖이거나 방문한 경우
                    if (nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;

                    // 해당 위치에 현재 상어보다 크기가 큰 물고기가 있는 경우 지나갈 수 없음
                    if (arr[nr][nc] > sharkSize) continue;

                    visited[nr][nc] = true;


                    // 물고기가 있는 경우 크기가 작으면 먹음
                    if (arr[nr][nc] > 0 && arr[nr][nc] < sharkSize) {
                        nearestFish.add(new FishInfo(nr, nc, time+1));
                    }

                    // 물고기가 있지만 크기가 같은 경우, 0인 경우는 지나가기만 함
                    dq.offer(new Position(nr, nc));
                }
            }

            if (!nearestFish.isEmpty()) {
                Collections.sort(nearestFish);
                return nearestFish.get(0);
            }
            time++;
        }

        return null;
    }

    static class Position {
        int r, c;

        public Position(final int r, final int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class FishInfo implements Comparable<FishInfo>{
        int r, c, time;

        public FishInfo(final int r, final int c, final int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public int compareTo(final FishInfo o) {
            if (this.time!=o.time) return this.time-o.time;
            if (this.r!=o.r) return this.r-o.r;
            return this.c-o.c;
        }
    }
}
