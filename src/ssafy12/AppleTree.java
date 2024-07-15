package ssafy12;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppleTree {

    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("res/AppleTree.txt"));
        Scanner sc = new Scanner(System.in);

        // N 입력받기
        int n = sc.nextInt();

        // NxN 땅 입력받기
        int[][] land = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                land[i][j] = sc.nextInt();
            }
        }

        // NxN 땅을 돌면서 얻을 수 있는 영양분 계산하기
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int result = land[i][j];
                for (int k = 0; k < 4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if (nx > -1 && nx < n && ny > -1 && ny < n) {
                        result += land[ny][nx];
                    }
                }
                // 최댓값과 비교해 최댓값 갱신하기
                if (result > max) {
                    max = result;
                }
            }
        }

        System.out.println(max);
    }

}
