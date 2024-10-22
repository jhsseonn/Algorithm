package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 17144 미세먼지 안녕!
public class Main_17144_미세먼지 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static Dust[][] room;
    static List<Pos> circulator;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 첫째줄 입력
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new Dust[R][C];
        circulator = new ArrayList<>();

        // 방 내부 입력(공기청정기, 미세먼지)
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = new Dust(Integer.parseInt(st.nextToken()), 0);
                if (room[i][j].origin==-1) circulator.add(new Pos(i, j));
            }
        }

        // T초 동안 반복
        for (int i = 0; i < T; i++) {
            microDust();
            circulate();
        }

        // 결과 출력
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int val = room[i][j].origin;
                if (val==-1) continue;
                ans += val;
            }
        }
        System.out.println(ans);
    }

    // 미세먼지 확산
    private static void microDust() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 확산되는 양: 5로 나눈 몫
                int origin = room[r][c].origin;
                int val = origin/5;
                int cnt = 0;

                // 인접한 4방향으로 확산
                for (int i = 0; i < 4; i++) {
                    int nr = r+dr[i];
                    int nc = c+dc[i];

                    // 공기청정기가 있거나 범위 밖이면 확산x
                    if (nr<0 || nr>=R || nc<0 || nc>=C) continue;
                    int dust = room[nr][nc].origin;
                    if (dust==-1) continue;

                    room[nr][nc].sum += val;
                    cnt+=1;
                }

                // 남은 양: A[r][c]-(A[r][c]/5*cnt)
                room[r][c].origin = origin-(val*cnt);
            }
        }

        // 각 칸의 먼지양 갱신
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                room[r][c].updateOriginValue();
            }
        }
    }

    // 공기청정기 바람 순환
    private static void circulate() {
        // 반시계 방향
        int[] udr = {1, 0, -1, 0};
        int[] udc = {0, -1, 0, 1};

        // 위쪽 순환 - 반시계 방향으로 밀기
        Queue<Pos> q = new ArrayDeque<>();
        Pos up = circulator.get(0);
        q.offer(up);
        int idx = 0;
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            int nr = cur.r+udr[idx];
            int nc = cur.c+udc[idx];
            if (nr<0 || nr>circulator.get(0).r || nc<0 || nc>=C ) {
                idx = (idx+1)%4;
                continue;
            }
            if (room[nr][nc].origin==-1) {
                break;
            }
            q.offer(new Pos(nr, nc));

            if (room[cur.r][cur.c].origin==-1) {  // 이전 칸이 공기청정기인 경우
                room[nr][nc].origin = 0;  // 먼지 없애고
                continue;
            }
            room[nr][nc].origin = room[cur.r][cur.c].origin;  // 이전 칸의 먼지를 지금 칸에 넣어줌
        }

        // 시계 방향
        int[] ddr = {1, 0, -1, 0};
        int[] ddc = {0, 1, 0, -1};

        // 아래쪽 순환 - 시계 방향으로 밀기
        Queue<Pos> q2 = new ArrayDeque<>();
        Pos down = circulator.get(1);
        q2.offer(down);
        int idx2 = 0;
        while(!q2.isEmpty()) {
            Pos cur = q2.poll();
            int nr = cur.r+ddr[idx2];
            int nc = cur.c+ddc[idx2];
            if (nr<circulator.get(1).r || nr>=R || nc<0 || nc>=C) {
                idx2 = (idx2+1)%4;
                continue;
            }
            if (room[nr][nc].origin==-1) {
                break;
            }
            q2.offer(new Pos(nr, nc));

            if (room[cur.r][cur.c].origin==-1) {  // 이전 칸이 공기청정기인 경우
                room[nr][nc].origin = 0;  // 먼지 없애고
                continue;
            }
            room[nr][nc].origin = room[cur.r][cur.c].origin;  // 이전 칸의 먼지를 지금 칸에 넣어줌
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Dust {
        int origin;
        int sum;

        public Dust(int origin, int sum) {
            this.origin = origin;
            this.sum = sum;
        }

        public void updateOriginValue() {
            this.origin += this.sum;
            this.sum = 0;
        }
    }
}
