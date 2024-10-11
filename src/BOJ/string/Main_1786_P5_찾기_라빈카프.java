package BOJ.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1786_P5_찾기_라빈카프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final long MOD = 1_000_000_009; // 32 bit 정수에 가까운 소수
        final long base = 26;

        char[] text, pattern;
        text = br.readLine().toCharArray();
        pattern = br.readLine().toCharArray();

        if (text.length < pattern.length) {
            System.out.println(0);
            return;
        }

        long pHash = 0, tHash = 0, power = 1;
        ArrayList<Integer> list = new ArrayList<>(5);

        /*
         * Robin fingerprint
         * - 라빈 카프 알고리즘에서 메이저하게 사용되는 해시 함수 기법
         *   hash = A0 + A1*base^1 + A2*base^2 + ...
         *
         */
        int tlen = text.length, plen = pattern.length;

        for (int i = 0; i < plen; i++) {
            pHash = (pHash * base) % MOD;
            pHash = (pHash + pattern[i]) % MOD;

            tHash = (tHash * base) % MOD;
            tHash = (tHash + text[i]) % MOD;

            if (i < plen - 1) {
                power = (power * base) % MOD;
            }
        }

        if (pHash == tHash) {
            list.add(1);
        }

        for (int i = 1, end = tlen - plen; i <= end; i++) {
            // window에서 맨 앞부분 빼고 맨 뒷부분 추가
            tHash = (((tHash - (text[i - 1] * power) % MOD + MOD) % MOD * base) % MOD + text[i + plen - 1]) % MOD;

            if (pHash == tHash) {
                list.add(i + 1);
            }
        }

        System.out.println(list.size());
        for (int idx : list) {
            System.out.print(idx+" ");
        }
    }
}
