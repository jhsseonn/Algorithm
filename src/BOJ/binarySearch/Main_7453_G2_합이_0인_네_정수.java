package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7453_G2_합이_0인_네_정수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] A, B, C, D;
    static int[] AB, CD;
    static long ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        AB = new int[N * N];
        CD = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[i * N + j] = A[i] + B[j];
                CD[i * N + j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        getAns();

        System.out.println(ans);
    }

    private static void getAns() {
        int ab = 0;
        int cd = N * N - 1;

        while (ab < N * N && cd >= 0) {
            int abValue = AB[ab];
            int cdValue = CD[cd];
            int sum = abValue + cdValue;
            if (sum < 0) {
                ab++;
            } else if (sum > 0) {
                cd--;
            } else {
                long abCnt, cdCnt;
                abCnt = cdCnt = 0;

                // AB에서 같은 값 개수 세기
                while (ab < N * N && AB[ab] == abValue) {
                    abCnt++;
                    ab++;
                }

                // CD에서 같은 값 개수 세기
                while (cd >= 0 && CD[cd] == cdValue) {
                    cdCnt++;
                    cd--;
                }

                ans += abCnt * cdCnt;
            }
        }
    }
}
