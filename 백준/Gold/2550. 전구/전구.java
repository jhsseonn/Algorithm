import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[2][N];  // 0: 스위치  1: 전구
        int[] lightIdx = new int[N];
        int[] dp = new int[N];
        int[] dpIdx = new int[N];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 스위치에 대해 전구 인덱스 배열 저장하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[0][i]==arr[1][j]) {
                    lightIdx[i] = j;
                    break;
                }
            }
        }

        int maxLen = 0;
        int lastIndex = 0;

        // 최장증가부분수열 구하기
        Arrays.fill(dp, 1);
        Arrays.fill(dpIdx, -1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (lightIdx[i]>lightIdx[j] && dp[i] < dp[j]+1) {
                    dp[i] = dp[j]+1;
                    dpIdx[i] = j;
                }
            }
            if (dp[i]>maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        while (lastIndex!=-1) {
            ans.add(arr[1][lightIdx[lastIndex]]);
            lastIndex = dpIdx[lastIndex];
        }

        Collections.sort(ans);
        sb.append(ans.size()).append("\n");
        for (Integer an : ans) {
            sb.append(an).append(" ");
        }

        System.out.println(sb);
    }
}