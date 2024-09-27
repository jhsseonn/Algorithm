package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 14,416kb / 96ms
 */
public class Main_2749_피보나치_수_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] arr;
    static long MOD;

    public static void main(String[] args) throws IOException {
        long N = Long.parseLong(br.readLine());
        MOD = 1_000_000L;
        arr = new long[2][2];
        System.out.println(fibo(N)[0][1]);
    }

    private static long[][] fibo(long n) {
        if (n == 1) {
            arr[0][0] = 1;
            arr[0][1] = 1;
            arr[1][0] = 1;
            arr[1][1] = 0;
            return arr;
        }

        long[][] temp = fibo(n / 2);
        if (n % 2 == 1) {
            return matrixMultiple(matrixMultiple(temp, temp), fibo(1));
        } else {
            return matrixMultiple(temp, temp);
        }
    }

    private static long[][] matrixMultiple(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] % MOD + a[0][1] * b[1][0] % MOD) % MOD;
        result[0][1] = (a[0][0] * b[0][1] % MOD + a[0][1] * b[1][1] % MOD) % MOD;
        result[1][0] = (a[1][0] * b[0][0] % MOD + a[1][1] * b[1][0] % MOD) % MOD;
        result[1][1] = (a[1][0] * b[0][1] % MOD + a[1][1] * b[1][1] % MOD) % MOD;
        return result;
    }
}
