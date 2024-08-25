package BOJ.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_G4_N_Queen2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] colNums;
    static int ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        colNums = new int[N + 1];
        setQueens(1);
        System.out.println(ans);
    }

    private static void setQueens(int rowNum) {
        // 백트래킹
        if (!isValid(rowNum - 1)) return;

        if (rowNum > N) {
            ans++;
            return;
        }

        // colNums 돌면서 rowNum 값을 넣어준다
        for (int i = 1; i < N + 1; i++) {
            colNums[rowNum] = i;
            setQueens(rowNum + 1);
        }
    }

    private static boolean isValid(int rowNum) {
        // 같은 행, 열, 대각선에 퀸이 존재한다면 재귀를 빠져나간다
        for (int i = 1; i < rowNum; i++) {
            if (colNums[rowNum] == colNums[i] || Math.abs(colNums[rowNum] - colNums[i]) == rowNum - i) return false;
        }
        return true;
    }
}
