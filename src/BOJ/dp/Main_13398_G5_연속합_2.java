package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13398_G5_연속합_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] start = new int[N + 1];
        int[] end = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = arr[1];
        // 처음부터 끝까지의 합
        start[1] = arr[1];
        for (int i = 2; i <= N; i++) {
            start[i] = Math.max(arr[i], start[i - 1] + arr[i]);
            ans = Math.max(ans, start[i]);
        }

        // 하나의 수를 제외했을 때 최댓값을 구하기 위한 끝에서부터 처음까지의 합
        end[N] = arr[N];
        for (int i = N - 1; i >= 1; i--) {
            end[i] = Math.max(arr[i], end[i + 1] + arr[i]);
        }

        for (int i = 2; i < N; i++) {
            int temp = start[i - 1] + end[i + 1];
            ans = Math.max(ans, temp);
        }

        System.out.println(ans);
    }
}
