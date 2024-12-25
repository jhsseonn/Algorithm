package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2342_G3_Dance_Dance_Revolution_풀이중 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int[][] dp = new int[5][5];
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[st.countTokens()+1];

        for (int i = 1; i <= st.countTokens(); i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i<arr.length; i++) {
            
        }
    }

    private int calculate(int before, int next) {
        if (before==0) return 2;
        else {
            if (Math.abs(before-next)==2) return 4;
            else if (Math.abs(before-next)==0) return 1;
            else return 3;
        }
    }

    private static class Pos {
        int left, right, strength;

        public Pos(int left, int right, int strength) {
            this.left = left;
            this.right = right;
            this.strength = strength;
        }
    }
}
