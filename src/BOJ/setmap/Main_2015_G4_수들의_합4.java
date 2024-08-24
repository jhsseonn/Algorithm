package BOJ.setmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 메모리: 35,616kb / 시간: 372ms
 */
public class Main_2015_G4_수들의_합4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[] sums;
    static int N;
    static long sum;
    static long K;
    static Map<Long, Long> sumMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        sums = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            sums[i] = sums[i-1] + Long.parseLong(st.nextToken());
        }

        getSums();

        System.out.println(sum);
    }

    private static void getSums() {
        sum = 0L;
        sumMap.put(0L, 1L);
        for (int i = 1; i<=N; i++) {
            sum += sumMap.getOrDefault(sums[i]-K, 0L);
            sumMap.put(sums[i], sumMap.getOrDefault(sums[i], 0L)+1);
        }
    }
}
