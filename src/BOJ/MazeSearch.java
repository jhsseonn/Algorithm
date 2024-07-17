package BOJ;

import java.util.*;
import java.io.*;

public class MazeSearch {

    static int[][] dest;

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];
        int[][] dis = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                dis[i][j] = -1;
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        dis[0][0] = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Pair p = q.poll();
            // 상하좌우 계산
            for (int i = 0; i < 4; i++) {
                assert p != null;
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위 밖인 경우
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                // 길이 아니거나 방문한 경우 PASS
                if (maze[nx][ny] == 0 || dis[nx][ny] != -1) {
                    continue;
                }

                q.offer(new Pair(nx, ny));
                dis[nx][ny] = dis[p.x][p.y]+1;
            }
        }

        System.out.print(dis[N-1][M-1]+1);
    }
}
