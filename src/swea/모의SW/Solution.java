package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int K;
    static int[][] magnet;
    static int[] red;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            K = Integer.parseInt(br.readLine());
            magnet = new int[4][8];
            red = new int[4];

            Arrays.fill(red, 0);  // 처음에는 모든 자석의 0번째 날이 빨간색 화살표에 위치함

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // k번만큼 자석 움직이기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                move(m, d);
            }

            sb.append("#").append(test_case).append(" ").append(getResult()).append("\n");
        }
        System.out.println(sb);
    }

    // 자석 움직이기
    private static void move(int m, int dir) {
        int firstR = getIndex(red[0]+2);
        int secondL = getIndex(red[1]-2);
        int secondR = getIndex(red[1]+2);
        int thirdL = getIndex(red[2]-2);
        int thirdR = getIndex(red[2]+2);
        int fourthL = getIndex(red[3]-2);

        int start = m-1;



        // 돌리는 자석이 0~3번째일 때
        if (start==0) {
            // 0번째, 1번째 비교
            if (magnet[0][firstR]!=magnet[1][secondL]) {
                if (dir==1) red[0] = getIndex(red[0]-1);
                else red[0] = getIndex(red[0]+1);
            }
            if (magnet[1][secondR]!=magnet[2][thirdL]) {
                if (dir==1) red[1] = getIndex(red[1]-1);
                else red[1] = getIndex(red[1]+1);
            }
            if (magnet[2][thirdR]!=magnet[3][fourthL]) {
                if (dir==1) {
                    red[2] = getIndex(red[2]-1);
                    red[3] = getIndex(red[3]+1);
                }
                else {
                    red[2] = getIndex(red[2]+1);
                    red[3] = getIndex(red[3]-1);
                }
            }
        }
    }

    private static int getIndex(int idx) {
        if (idx<0) return idx+8;
        else if (idx>=8) return idx-8;
        return idx;
    }

    // 빨간색 화살표에 위치한 날의 극에 따라 최종 점수 구하기
    private static int getResult() {
        int result = 0;
        for (int i = 0; i < red.length; i++) {
            if (magnet[i][red[i]] == 0) result += 0;
            else {
                if (i == 0) result += 1;
                else if (i == 1) result += 2;
                else if (i == 2) result += 4;
                else result += 8;
            }
        }
        return result;
    }
}
