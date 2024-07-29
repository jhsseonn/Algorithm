package ssafy12study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumDivideGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int SIZE = 1000001;

        // 플레이어 명 수 받기
        int N = Integer.parseInt(st.nextToken());

        // 각 플레이어의 숫자 받기
        st = new StringTokenizer(br.readLine());
        boolean[] cards = new boolean[SIZE];
        int[] exsists = new int[N];
        for (int i = 0; i < N; i++) {
            exsists[i] = Integer.parseInt(st.nextToken());
            cards[exsists[i]] = true;
        }

        int[] result = new int[SIZE];
        for (int i : exsists) {
            for (int j = i * 2; j < SIZE; j += i) {
                if (cards[j]) {
                    result[i] += 1;
                    result[j] -= 1;
                }
            }
        }

        for (int i : exsists) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}
