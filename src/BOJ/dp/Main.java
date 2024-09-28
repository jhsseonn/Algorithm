package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14,196kb / 104ms
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N, MOD;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        dp = new long[2][2];
        MOD = 1_000_000_007;

        if (N%2!=0) {
            System.out.println(0);
        } else {
            if(N==2) System.out.println(3);
            else {
                long[] base = {3, 1};
                System.out.println(matrixMulti1(getBase(N/2), base)[1]);
            }
        }
    }

    private static long[][] getBase(long n) {
        if (n==1) {
            dp[0][0] = 4;
            dp[0][1] = -1;
            dp[1][0] = 1;
            dp[1][1] = 0;
            return dp;
        } else {
            long[][] temp = getBase(n/2);
            if (n%2==0) {
                return matrixMulti2(temp, temp);
            } else {
                return matrixMulti2(matrixMulti2(temp, temp), getBase(1));
            }
        }
    }

    // 계수 패턴 행렬곱
    private static long[][] matrixMulti2(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] % MOD + a[0][1] * b[1][0] % MOD+MOD) % MOD;
        result[0][1] = (a[0][0] * b[0][1] % MOD + a[0][1] * b[1][1] % MOD+MOD) % MOD;
        result[1][0] = (a[1][0] * b[0][0] % MOD + a[1][1] * b[1][0] % MOD+MOD) % MOD;
        result[1][1] = (a[1][0] * b[0][1] % MOD + a[1][1] * b[1][1] % MOD+MOD) % MOD;
        return result;
    }

    // base와의 행렬곱으로 정답 구하기
    private static long[] matrixMulti1(long[][] a, long[] b) {
        long[] result = new long[2];
        result[0] = (a[0][0]*b[0]%MOD + a[0][1]*b[1]%MOD+MOD)%MOD;
        result[1] = (a[1][0]*b[0]%MOD + a[1][1]*b[1]%MOD+MOD)%MOD;
        return result;
    }
}
