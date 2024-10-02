package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 27,548kb / 160ms
 */
public class Solution_3307_D3_최장_증가_부분_수열_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] LIS = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            Arrays.fill(LIS, 1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j]<arr[i] && LIS[i]<LIS[j]+1) {
                        LIS[i] = LIS[j]+1;
                    }
                }
                if (max<LIS[i]) max = LIS[i];
            }

            sb.append("#").append(test_case).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
