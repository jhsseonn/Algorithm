package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 28460kb / 312ms
 */
public class Main_2467_G5_용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static long[] solvent;
    static long minSum;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        solvent = new long[N];
        minSum = Long.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solvent[i] = Long.parseLong(st.nextToken());
        }

        int start = 0;
        int end = solvent.length-1;

        long ans1 = 0;
        long ans2 = 0;

        while (start!=end) {
            long sum = solvent[start] + solvent[end];
            if (minSum>Math.abs(sum)) {
                minSum = Math.abs(sum);
                ans1 = solvent[start];
                ans2 = solvent[end];
            }
            if (sum<0) start+=1;
            if (sum>0) end-=1;
            if (sum==0) break;
        }

        System.out.println(ans1+" "+ans2);
    }
}
