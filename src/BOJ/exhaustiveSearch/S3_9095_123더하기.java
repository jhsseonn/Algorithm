package BOJ.exhaustiveSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_9095_123더하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int sum = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            getSum(N);
            System.out.println(count);
            count = 0;
        }
    }

    static void getSum(int N) {
        if (sum == N) {
            count++;
            return;
        } else if (sum > N) return;

        getSum(N - 1);
        getSum(N - 2);
        getSum(N - 3);
    }
}
