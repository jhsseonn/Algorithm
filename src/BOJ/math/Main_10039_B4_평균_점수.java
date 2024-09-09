package BOJ.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10039_B4_평균_점수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            int score = Integer.parseInt(br.readLine());
            if (score < 40) score = 40;
            total+=score/5;
        }

        System.out.println(total);
    }
}
