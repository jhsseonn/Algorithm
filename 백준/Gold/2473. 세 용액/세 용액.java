import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long minSum;
    static long[] solvent, res;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        solvent = new long[N];
        minSum = Long.MAX_VALUE;
        res = new long[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solvent[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solvent);

        for (int i = 0; i < N-2; i++) {
            int start =i;
            int mid = i+1;
            int end = solvent.length-1;

            while(mid<end) {
                long curSum = solvent[start]+solvent[mid]+solvent[end];
                if (minSum>Math.abs(curSum)) {  // 최솟값 갱신
                    minSum = Math.abs(curSum);
                    res[0] = solvent[start];
                    res[1] = solvent[mid];
                    res[2] = solvent[end];
                }

                if (curSum<0) mid+=1;
                else if (curSum>0) end-=1;
                else break;
            }
        }


        Arrays.sort(res);

        for (long a:res) {
            System.out.print(a+" ");
        }
    }
}
