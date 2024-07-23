package ssafy12study.week1;

import java.io.*;
import java.util.*;

public class GetPrimeNum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] primes = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            primes[i] = i;
        }

        for (int i = 2; i < N + 1; i++) {
            if (primes[i] == 0) continue;
            if (primes[i] >= M && primes[i] <= N) {
                System.out.println(primes[i]);
            }
            for (int j = 2 * i; j <= N; j += i) {
                if (primes[j] != 0) {
                    primes[j] = 0;
                }

            }
        }
    }
}
