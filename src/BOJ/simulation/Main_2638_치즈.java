package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2638_치즈 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static int[][] arr;
    static List<Cheese> cheeses;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        cheeses = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    cheeses.add(new Cheese(i, j, false));  // 우선 공기와 닿아있는 가장 자리 0으로 초기화
                }
            }
        }

        int time = 0;
        while (true) {
            time++;



            int cntCheese = cntCheese();
            if (cntCheese == 0) break; // 모든 치즈가 녹은 경우
        }

        // 치즈가 모두 녹아 없어지는데 걸리는 시간 구하기
        System.out.println(time - 1);
    }

    // 치즈 안쪽 찾기(BFS)
    private static int[][] findInside() {
        int[][] cheeseCopy = new int[R][C];
        for (int i = 0; i < R; i++) {
            cheeseCopy[i] = arr[i].clone();
        }



        return cheeseCopy;
    }

    // 공기와 닿아있는 가장자리 계산
    private static int cntCheese() {
        int[][] cheeseCopy = new int[R][C];
        for (int i = 0; i < R; i++) {
            cheeseCopy[i] = arr[i].clone();
        }

        int cnt = 0;
        for (Cheese cheese : cheeses) {
            if (cheese.melted) continue;  // 이미 녹은 치즈는 고려하지 않는다.
            int r = cheese.r;
            int c = cheese.c;
            int edge = 0;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (cheeseCopy[nr][nc] == 1) continue;
                edge++;
            }

            if (edge >= 2) cheese.melted = true;  // 4변 중 2변 이상이 공기와 접촉한 경우 한시간 만에 녹아 없어짐
            cnt++;
        }

        for (Cheese cheese : cheeses) {
            if (cheese.melted) {
                cheeseCopy[cheese.r][cheese.c] = 0;  // 녹은 치즈 상태 바꿔주기
            }
        }

        arr = cheeseCopy;  // 반영
        return cnt;
    }

    // 치즈 위치, 공기와 접촉한 변의 개수
    static class Cheese {
        int r, c;
        boolean melted;

        public Cheese(int r, int c, boolean melted) {
            this.r = r;
            this.c = c;
            this.melted = melted;
        }
    }
}
