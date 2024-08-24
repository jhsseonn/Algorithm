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
            Car c = null;

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
            input = new char[N];
            String dir = br.readLine();
            for (int i = 0; i < N; i++) {
                char command = dir.charAt(i);
                prototype(command, c);
            }

            sb.append("#").append(test_case).append(" ");
            for (int i = 0; i < R; i++) {
                sb.append(map[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void prototype(char command, Car car) {
        int r = car.r;
        int c = car.c;
        if (directions.contains(command)) {  // 방향을 옮기는 명령어이면
            if (command=='U') {  // 전차의 방향을 바꾸고
                car.setDir('^');
                map[r][c] = '^';
            } else if (command=='D') {
                car.setDir('v');
                map[r][c] = 'v';
            } else if (command=='L') {
                car.setDir('<');
                map[r][c] = '<';
            } else if (command=='R') {
                car.setDir('>');
                map[r][c] = '>';
            }
            move(car);  // 전차를 움직인다
        }
        if (command=='S') { // 포탑을 쏜다
            shooting(car);
        }
    }

    private static void move(Car car) {
        int r = car.r;
        int c = car.c;
        char dir = car.dir;
        if (dir=='^') {
            if (r-1 >-1 && r-1<R && map[r-1][c]=='.') {
                map[r][c] = '.';
                map[r-1][c]='^';
                car.setR(r-1);
            }
        } else if (dir=='v') {
            if (r+1 >-1 && r+1<R && map[r+1][c]=='.') {
                map[r][c] = '.';
                map[r+1][c]='v';
                car.setR(r+1);
            }
        } else if (dir=='<') {
            if (c-1 >-1 && c-1<C && map[r][c-1]=='.') {
                map[r][c] = '.';
                map[r][c-1]='<';
                car.setC(c-1);
            }
        } else if (dir=='>') {
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
        int r = car.r;
        int c = car.c;
        int dir = car.dir;

        if (dir == '^') {
            if (r==0) return;
            for (int i = r - 1; i >= 0; i--) {
                if (map[i][c] == '#') {
                    break;
                } else if (map[i][c] == '*') {
                    map[i][c] = '.';
                    break;
                }
            }
        } else if (dir == 'v') {
            if (r==R-1) return;
            for (int i = r+1; i < R; i++) {
                if (map[i][c] == '#') {
                    break;
                } else if (map[i][c] == '*') {
                    map[i][c] = '.';
                    break;
                }
            }
        } else if (dir == '<') {
            if (c==0) return;
            for (int i = c-1; i >= 0; i--) {
                if (map[r][i] == '#') {
                    break;
                } else if (map[r][i] == '*') {
                    map[r][i] = '.';
                    break;
                }
            }
        } else if (dir == '>') {
            if (c==C-1) return;
            for (int i = c+1; i < C; i++) {
                if (map[r][i] == '#') {
                    break;
                } else if (map[r][i] == '*') {
                    map[r][i] = '.';
                    break;
                }
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
