package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4014_모의_활주로_건설 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, X, result;
    static int[][] arrOrigin, arrReverse;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            result = 0;
            arrOrigin = new int[N][N];
            arrReverse = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arrOrigin[i][j] = Integer.parseInt(st.nextToken());
                    arrReverse[j][i] = arrOrigin[i][j];
                }
            }

            start(arrOrigin);
            start(arrReverse);

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void start(int[][] arr) {
        for (int i = 0; i < N; i++) {
            boolean isValid = false;  // 해당 행의 활주로 건설 가능 여부
            int sameHeight = 1;

            // 경사로 설치
            for (int j = 1; j < N; j++) {
                // 이전 칸과 지금 칸이 다른 경우
                if (arr[i][j] != arr[i][j - 1]) {
                    if (arr[i][j-1]-arr[i][j]==1) {  // 내려가는 경사로 만들기
                        sameHeight = 0;
                        if (isValid(arr, i, j, arr[i][j])) i+=X-1;
                        else {
                            isValid = false;
                            break;
                        }
                    } else if (arr[i][j]-arr[i][j-1]==1) {  // 올라가는 경사로 만들기
                        if (sameHeight<X) {
                            isValid = false;
                            sameHeight = 1;
                            break;
                        }
                        sameHeight = 1;
                    } else {  // 경사로를 만들 수 없음
                        isValid = false;
                        sameHeight = 1;
                        break;
                    }
                } else {
                    sameHeight++;
                }
            }
            if (isValid) result++;
        }
    }

    private static boolean isValid(int[][] arr, int x, int y, int height) {
        int k = 1;
        while(y+k<N && arr[x][y+k]==height && k<X) k++;
        if (k==X) return true;
        else return false;
    }
}
