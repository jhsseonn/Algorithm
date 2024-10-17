package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 20,592kb / 196ms
 */
public class Main_15732_G2_도토리_숨기기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[][] rules = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        int result = binarySearch(1, N, D, rules);
        System.out.println(result);
    }

    private static int binarySearch(int start, int end, int D, int[][] rules) {
        int result = end;
        while (start <= end) {
            int mid = (start + end) / 2;

            long totalArcons = getTotalArcons(mid, rules);
            if (totalArcons >= D) {
                result = mid;
                end = mid - 1;
            } else start = mid + 1;

        }

        return result;
    }

    // 주어진 상자까지 채울 수 있는 도토리 수 계산
    private static long getTotalArcons(int mid, int[][] rules) {
        long total = 0;

        for (int[] r : rules) {
            int A = r[0];
            int B = r[1];
            int C = r[2];

            if (mid >= A) total += (Math.min(mid, B) - A) / C + 1;
        }


        return total;
    }
}
