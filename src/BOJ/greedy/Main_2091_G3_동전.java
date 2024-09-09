package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2091_G3_동전 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int S, A, B, C, D;
    static Coin[] coins;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        coins = new Coin[4];
        int idx = 0;

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        coins[idx++] = new Coin(25, D);
        coins[idx++] = new Coin(10, C);
        coins[idx++] = new Coin(5, B);
        coins[idx] = new Coin(1, A);

        S = (A + 5*B + 10*C + 25*D)-X;

        getMinOfSpare();
        if (coins[3].cnt==-1) System.out.println("0, 0, 0, 0");
        else {
            for (int i = 3; i >=0; i--) {
                System.out.print(coins[i].cnt+" ");
            }
        }
    }

    private static void getMinOfSpare() {
        if (S < 0) {
            coins[3].setCoincnt(-1);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int amount = coins[i].amount;
            int cnt = coins[i].cnt;

            // 해당 코인을 사용할 수 없는 경우
            if (amount > S) continue;
            if (cnt==0) continue;

            int spend = S/amount;
            cnt -= spend;
            if (cnt<0) {
                if (i==3) coins[i].setCoincnt(-1);
                else {
                    S=S-spend*amount;
                    cnt = 0;
                    coins[i].setCoincnt(cnt);
                }

                return;
            }

            S=S-spend*amount;

            // 코인 개수 업데이트
            coins[i].cnt = cnt;
            coins[i].setCoincnt(cnt);

        }
    }

    private static class Coin {
        int amount;
        int cnt;
        public Coin(int amount, int cnt) {
            this.amount = amount;
            this.cnt = cnt;
        }

        public void setCoincnt(int cnt) {
            this.cnt = cnt;
        }
    }
}
