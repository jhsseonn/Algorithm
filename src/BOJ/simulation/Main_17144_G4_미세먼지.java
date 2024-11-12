package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 39,200kb / 336ms
 */
public class Main_17144_G4_미세먼지 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static int[][] circulator;
    static Dust[][] room;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 첫째줄 입력
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new Dust[R][C];
        circulator = new int[2][2];

        int cidx = 0;
        // 방 내부 입력(공기청정기, 미세먼지)
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = new Dust(Integer.parseInt(st.nextToken()), 0);
                if (room[i][j].origin == -1) {
                    circulator[cidx++] = new int[]{i, j};
                }
            }
        }

        // T초 동안 반복
        for (int i = 1; i <= T; i++) {
            microDust();
        }

        // 결과 출력
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int val = room[i][j].origin;
                if (val == -1) continue;
                ans += val;
            }
        }
        System.out.println(ans);
    }

    // 미세먼지 확산
    private static void microDust() {
        Dust[][] temp = new Dust[R][C];
        for (int i = 0; i < R; i++) {
            temp[i] = room[i].clone();
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 확산되는 양: 5로 나눈 몫
                int origin = room[r][c].origin;
                if (origin < 5) continue;
                int val = origin / 5;
                int cnt = 0;

                // 인접한 4방향으로 확산
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    // 공기청정기가 있거나 범위 밖이면 확산x
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C || room[nr][nc].origin == -1) continue;

                    temp[nr][nc].sum += val;
                    cnt++;
                }

                temp[r][c].origin -= val * cnt;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j].updateOriginalValue();
            }
        }

        // 공기청정기 바람 순환
        circulate(temp);
    }

    // 공기청정기 바람 순환
    private static void circulate(Dust[][] temp) {
        // 위쪽 순환 - 반시계 방향으로 밀기
        int idx = 0;
        int[] udr = {0, -1, 0, 1};
        int[] udc = {1, 0, -1, 0};
        int dor = circulator[0][0];
        int doc = circulator[0][1];
        while (true) {
            int nr = dor + udr[idx];
            int nc = doc + udc[idx];
            if (nr < 0 || nr > circulator[0][0] || nc < 0 || nc >= C) {
                idx = (idx + 1) % 4;
                continue;
            }
            if (nr == circulator[0][0] && nc == circulator[0][1]) break;
            if (room[dor][doc].origin==-1) {
                temp[nr][nc]=new Dust(0, 0);
            } else {
                temp[nr][nc] = room[dor][doc];
            }
            dor = nr;
            doc = nc;
        }

        // 아래쪽 순환 - 시계 방향으로 밀기
        idx = 0;
        int[] ddr = {0, 1, 0, -1};
        int[] ddc = {1, 0, -1, 0};
        int ur = circulator[1][0];
        int uc = circulator[1][1];
        while (true) {
            int nr = ur + ddr[idx];
            int nc = uc + ddc[idx];
            if (nr < circulator[1][0] || nr >= R || nc < 0 || nc >= C) {
                idx = (idx + 1) % 4;
                continue;
            }
            if (nr == circulator[1][0] && nc == circulator[1][1]) break;
            if (room[ur][uc].origin==-1) {
                temp[nr][nc]=new Dust(0, 0);
            } else {
                temp[nr][nc] = room[ur][uc];
            }
            ur = nr;
            uc = nc;
        }

        room = temp;  // 방에 현재 상태 업데이트
    }

    static class Dust {
        int origin;
        int sum;

        public Dust(final int origin, final int sum) {
            this.origin = origin;
            this.sum = sum;
        }

        public void updateOriginalValue() {
            this.origin += this.sum;
            this.sum = 0;
        }
    }
}
