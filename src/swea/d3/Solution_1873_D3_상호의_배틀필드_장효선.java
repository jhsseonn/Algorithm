package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1873_D3_상호의_배틀필드_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int R, C;
    static char[][] map;
    static char[] input;
    static List<Character> directions = new ArrayList<>(Arrays.asList('U', 'D', 'L', 'R'));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            List<Character> cars = new ArrayList<>(Arrays.asList('^', 'v', '<', '>'));
            Car c;

            for (int i = 0; i < R; i++) {
                String str = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = str.charAt(j);
                    if (cars.contains(map[i][j])) {
                        c = new Car(i, j, map[i][j]);
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            for (int i = 0; i < N; i++) {
                input[i] = str.charAt(i);
            }

            int result = 0;

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void prototype(char[] input, int N, Car car) {
        for (int i = 0; i < N; i++) {
            if (directions.contains(input[i])) {
                char dir = car.dir;
                if (dir=='U') {
                    car.setDir('U');
                } else if (dir=='D') {
                    car.setDir('D');
                } else if (dir=='L') {
                    car.setDir('L');
                } else if (dir=='R') {
                    car.setDir('R');
                }
                move(car);
                continue;
            }
            if (input[i]=='S') { // 포탑을 쏜다


            }
        }
    }

    private static void move(Car car) {
        int r = car.r;
        int c = car.c;
        char dir = car.dir;
        if (dir=='U') {
            if (r-1 >-1 && r-1<R && map[r-1][c]=='.') {
                map[r][c] = '.';
                map[r-1][c]='^';
                car.setR(r-1);
            }
        } else if (dir=='D') {
            if (r+1 >-1 && r+1<R && map[r+1][c]=='.') {
                map[r][c] = '.';
                map[r+1][c]='v';
                car.setR(r+1);
            }
        } else if (dir=='L') {
            if (c-1 >-1 && c-1<C && map[r][c-1]=='.') {
                map[r][c] = '.';
                map[r][c-1]='<';
                car.setC(c-1);
            }
        } else if (dir=='R') {
            if (c+1 >-1 && c+1<C && map[r][c+1]=='.') {
                map[r][c] = '.';
                map[r][c+1]='>';
                car.setC(c+1);
            }
        }
    }

    private static void shooting(Car car) {
        // * : 벽돌로 만들어진 벽은 포탑으로 뚫을 수 있다
        // # : 강철로 만들어진 벽은 뚫을 수 없다
        // - : 물에는 들어갈 수 없다.
        int r = car.r;
        int c = car.c;
        int dir = car.dir;

        if (dir=='U') {
            for (int i = r-1; i >= 0; i--) {

            }
        }


    }

    private static class Car {
        int r;
        int c;
        char dir;

        public Car(int r, int c, char dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public void setR(int r) {
            this.r = r;
        }
        public void setC(int c) {
            this.c = c;
        }
        public void setDir(char dir) {
            this.dir = dir;
        }
    }
}
