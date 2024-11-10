package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SectionSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // N 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 표 입력받기
        int[][] table = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                table[i][j] = table[i][j-1]+Integer.parseInt(st.nextToken());
            }
        }

        // 시작 좌표, 끝 좌표 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // table 돌면서 sumTable의 좌표에 해당하는 범위 내 합 구하기
            int result = 0;
            for (int j = x1; j < x2+1; j++) {
                result = result + (table[j][y2]-table[j][y1-1]);
            }
            sb.append(result+"\n");
        }
        System.out.print(sb);
    }
}
