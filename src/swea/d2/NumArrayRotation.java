package swea.d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class NumArrayRotation {

    public static int[][] Rotation(int[][] arr, int N) {
        int[][] result = new int[N][N];

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                result[j][k] = arr[N - 1 - k][j];
            }
        }

        return result;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringBuffer sb = new StringBuffer();

            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] arr90 = Rotation(arr, N);
            int[][] arr180 = Rotation(arr90, N);
            int[][] arr270 = Rotation(arr180, N);

            sb.append("#").append(test_case).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(arr90[i][j]);
                }
                sb.append(" ");

                for (int j = 0; j < N; j++) {
                    sb.append(arr180[i][j]);
                }
                sb.append(" ");

                for (int j = 0; j < N; j++) {
                    sb.append(arr270[i][j]);
                }
                sb.append("\n");
            }

            System.out.print(sb);
        }
    }
}
