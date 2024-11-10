package BOJ.exhaustiveSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_1914_하노이탑 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<int[]> fromtovia;

    private static void hanoi(int N, int from, int via, int to) {
        if (N==1) {
            fromtovia.add(new int[] {from, to});
        } else {
            hanoi(N-1, from, to, via);
            fromtovia.add(new int[] {from, to});
            hanoi(N-1, via, from, to);
        }
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        fromtovia = new ArrayList<>();

        BigInteger num = new BigInteger("2");

//        hanoi(N, 1, 2, 3);
        sb.append(num.pow(N).subtract(new BigInteger("1"))).append("\n");
        if (N<=20) {
            hanoi(N, 1, 2, 3);
            for (int i = 0; i < fromtovia.size(); i++) {
                sb.append(fromtovia.get(i)[0]).append(" ").append(fromtovia.get(i)[1]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
