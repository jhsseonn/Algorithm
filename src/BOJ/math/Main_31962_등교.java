package BOJ.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_31962_등교 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int maxTime = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int wait = Integer.parseInt(st.nextToken());
            int run = Integer.parseInt(st.nextToken());
            int totalTime = wait+run;
            if (totalTime>X) continue;
            maxTime = Math.max(maxTime, wait);
        }

        if (maxTime==Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(maxTime);
    }
}
