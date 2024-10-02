package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 21,540kb / 133ms
 */
public class Solution_3307_D3_최장_증가_부분_수열_이진탐색_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] LIS = new int[N];
            int size = 0;
            LIS[size++] = arr[0];
            for (int i = 1; i < N; i++) {
                int temp = Arrays.binarySearch(LIS, 0, size, arr[i]);
                temp = Math.abs(temp)-1;
                LIS[temp] = arr[i];
                if (temp==size) {
                    size++;
                }
            }

            sb.append("#").append(test_case).append(" ").append(size).append("\n");
        }
        System.out.println(sb);
    }
}
