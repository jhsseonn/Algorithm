package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 17,044kb / 128ms
 */
public class Main_1351_G5_무한_수열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long P, Q;
    static Map<Long, Long> sequence;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        sequence = new HashMap<>();

        System.out.println(getSequence(N));
    }

    private static long getSequence(long N) {
        if (N==0) {
            sequence.put(N, 1L);
            return 1;
        } else {
            long a = N/P;
            long b = N/Q;
            sequence.put(a, getSequence(a));
            if (sequence.get(b)!=null) {
                return sequence.get(a)+sequence.get(b);
            } else {
                return(sequence.get(a) + getSequence(b));
            }
        }
    }
}
