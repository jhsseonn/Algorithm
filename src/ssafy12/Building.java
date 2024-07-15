package ssafy12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Building {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/building.txt"));
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 T 받기
        int T = sc.nextInt();

        for (int tc = 1; tc < T + 1; tc++) {
            // 부지 변의 길이(배열 크기) N 받기
            int N = sc.nextInt();

            // 부지 입력받기
            char[][] city = new char[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    city[i][j] = sc.next().charAt(0);
                }
            }

            // 상하좌우 대각선 인접구획 구하기
            int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
            int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};

            int maxHeight = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (city[i][j] == 'G') {
                        continue;
                    }

                    int height = 0;
                    for (int k = 0; k < 8; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        // 상하좌우 대각선 인접구획에 공원 조성 단지가 있다면 2층 높이,
                        if (nr > -1 && nr < N && nc > -1 && nc < N && city[nr][nc] == 'G') {
                            height = 2;
                            break;
                        }
                    }
                    // 없다면 현 위치의 가로 위치에 있는 빌딩구획과 세로 위치의 빌딩 구획 수를 더한 크기만큼 빌딩을 세운다
                    if (height == 0) {
                        for (int k = 0; k < N; k++) {
                            if (city[i][k] == 'B')
                                height++;
                            if (city[k][j] == 'B')
                                height++;
                        }
                        // 중복으로 더한 구역을 -1 해준다
                        height--;
                    }
                    // 세운 빌딩의 높이가 max 빌딩 높이보다 높은 경우 저장
                    if (height > maxHeight) {
                        maxHeight = height;
                    }
                }
            }

            // max 출력
            System.out.println("#" + tc + " " + maxHeight);
        }
    }
}
