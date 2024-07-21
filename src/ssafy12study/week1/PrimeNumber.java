package ssafy12study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimeNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] numArray = new int[N + 1];

        // 2부터 N까지 모든 정수를 적는다
        for (int i = 2; i <= N; i++) {
            numArray[i] = i;
        }

        for (int i = 2; i <= N; i++) {
            if (numArray[i] == 0) continue; // 이미 지운 수 건너뛰기
            // P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
            for (int j = i; j < N + 1; j += i) {
                if (numArray[j] != 0) {
                    numArray[j] = 0;
                    K--;
                }
                if (K == 0) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
