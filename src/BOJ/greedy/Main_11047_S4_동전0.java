package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 메모리: 14,216kb / 시간: 104ms
 * 처음에 K가 0이 되는 경우에 함수 리턴을 안해줘서 틀렸다,,
 */
public class Main_11047_S4_동전0 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, ans;
    static Integer[] coins;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new Integer[N];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins, Collections.reverseOrder());

        getMinCoin();

        System.out.println(ans);
    }

    private static void getMinCoin() {
        for (int coin:coins) {
            if (coin > K) continue;
            ans+=K/coin;
            K %= coin;
            if (K==0) return;
        }
    }
}
