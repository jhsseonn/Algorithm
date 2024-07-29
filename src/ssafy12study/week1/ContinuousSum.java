package ssafy12study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ContinuousSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int SIZE = 4_000_001;
        boolean[] isPrime = new boolean[SIZE];
        int[] primes = new int[SIZE];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        int idx = 0;
        for (int i = 2; i < SIZE; i++) {
            if (!isPrime[i]) continue;
            for (int j = i*2; j<SIZE; j+=i) {
                isPrime[j] = false;
            }
            primes[idx] = i;
            idx++;
        }

        int result = 0;
        for (int i = 0; i<primes.length; i++) {
            int sum = 0;
            int j = i;
            while (sum <= N && j <= N) {
                sum+=primes[j];
                if (sum == N) {
                    result+=1;
                    break;
                }
                j++;
            }
        }

        System.out.println(result);
    }
}
