package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import javax.swing.plaf.DesktopIconUI;

// 17144 미세먼지 안녕!
public class Main_17144_미세먼지 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static int[][] circulator;
    static Dust[][] room;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

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
                if (room[i][j].origin==-1) {
                    circulator[cidx++] = new int[] {i, j};
                }
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
        Dust[][] temp = new Dust[R][C];
        for (int i = 0; i < R; i++) {
            temp[i] = room[i].clone();
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 확산되는 양: 5로 나눈 몫
                int origin = room[r][c].origin;
                if (origin<5) continue;
                int val = origin/5;
                int cnt = 0;

                // 인접한 4방향으로 확산
                for (int i = 0; i < 4; i++) {
                    int nr = r+dr[i];
                    int nc = c+dc[i];

                    // 공기청정기가 있거나 범위 밖이면 확산x
                    if (nr<0 || nr>=R || nc<0 || nc>=C || room[nr][nc].origin==-1) continue;

                    temp[nr][nc].sum += val;
                    cnt++;
                }

                temp[r][c].origin -= val*cnt;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j].updateOriginalValue();
            }
        }

        // 각 칸의 먼지양 갱신
        room = temp;
    }

    // 공기청정기 바람 순환
    private static void circulate() {
        Dust[][] temp = new Dust[R][C];
        for (int i = 0; i < R; i++) {
            temp[i] = room[i].clone();
        }

        // 위쪽 순환 - 반시계 방향으로 밀기
        int idx = 0;
        int ur = circulator[0][0];
        int uc = circulator[0][1];
        while(true) {
            int nr = ur+dr[idx];
            int nc = uc+dc[idx];
            if (nr<0 || nr>circulator[0][0] || nc<0 || nc>=C) {
                idx = (idx+1)%4;
                continue;
            }
            if (nr==circulator[0][0] && nc==circulator[0][1]) break;
            temp[nr][nc] = room[ur][uc];
            ur = nr;
            uc = nc;
        }
        temp[ur][uc].origin=-1;

        // 아래쪽 순환 - 시계 방향으로 밀기
        int dor = circulator[1][0];
        int doc = circulator[1][1];
        idx = 0;
        while(true) {
            int nr = dor+dr[(idx+2)%4];
            int nc = doc+dc[(idx+2)%4];
            if (nr<circulator[1][0]|| nr>=R || nc<0 || nc>=C) {
                idx = (idx+1)%4;
                continue;
            }
            if (nr==circulator[1][0] && nc==circulator[1][1]) break;
            temp[nr][nc] = room[dor][doc];
            dor = nr;
            doc = nc;
        }
        temp[dor][doc].origin = -1;

        room = temp;
    }

    static class Dust {
        int origin;
        int sum;

        public Dust(final int origin, final int sum) {
            this.origin = origin;
            this.sum = sum;
        }

        public void updateOriginalValue() {
            this.origin+=this.sum;
            this.sum = 0;
        }
    }
}
