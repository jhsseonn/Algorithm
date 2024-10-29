package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 14,300kb / 116ms
 */
public class Main_30805_G4_사전_순_최대_공통_부분_수열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> res;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        res = new ArrayList<>();
        getMaxNum(A, B);

        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }

    private static void getMaxNum(List<Integer> a, List<Integer> b) {
        if (a.isEmpty() || b.isEmpty()) {
            return;
        }

        int aMax = Integer.MIN_VALUE;
        int aIdx = 0;
        for (int i = 0; i < a.size(); i++) {
            if (aMax < a.get(i)) {
                aMax = a.get(i);
                aIdx = i;
            }
        }
        int bMax = Integer.MIN_VALUE;
        int bIdx = 0;
        for (int i = 0; i < b.size(); i++) {
            if (bMax < b.get(i)) {
                bMax = b.get(i);
                bIdx = i;
            }
        }
        if (aMax == bMax) {
            res.add(aMax);
            getMaxNum(a.subList(aIdx + 1, a.size()), b.subList(bIdx + 1, b.size()));
        } else if (aMax > bMax) {
            a.remove(aIdx);
            getMaxNum(a, b);
        } else {
            b.remove(bIdx);
            getMaxNum(a, b);
        }
    }
}
