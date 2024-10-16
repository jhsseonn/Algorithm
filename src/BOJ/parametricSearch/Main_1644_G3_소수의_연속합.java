package BOJ.parametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 26,816kb / 188ms
 */
public class Main_1644_G3_소수의_연속합 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int count, N;
    static boolean[] arr;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1];
        count = 0;

        for (int i = 0; i <= N; i++) {
            arr[i] = true;
        }
        arr[0] = arr[1] = false;

        getPrimeNums();

        for (int i = 2; i <= N; i++) {
            if (arr[i]) primes.add(i);
        }

        getCount();

        System.out.println(count);
    }

    private static void getCount() {
        int start = 0;
        int end = 0;
        int sum = 0;
        int size = primes.size();

        while (true) {
            if (sum >= N) {
                if (sum == N) count++;

                if (start < size) {
                    sum -= primes.get(start);
                    start++;
                } else break;
            } else {
                if (end == size) break;
                sum += primes.get(end);
                end++;
            }
        }

    }

    private static void getPrimeNums() {
        for (int i = 2; i <= N; i++) {
            if (!arr[i]) continue;
            for (int j = 2 * i; j <= N; j += i) {
                arr[j] = false;
            }
        }
    }
}

