package BOJ.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_30805 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 공통 부분 수열 구하기
        List<Integer> commonNums = new ArrayList<>();
        int aIdx = 0, bIdx = 0;
        while (aIdx<N && bIdx<M) {
            if (A[aIdx]==B[bIdx]) {
                commonNums.add(A[aIdx]);
                aIdx++;
                bIdx++;
            } else {
                int sameAIdx = aIdx, sameBIdx = bIdx;
                while (sameBIdx < M && A[aIdx]!=B[sameBIdx]) {
                    sameBIdx++;
                }
                while (sameAIdx < N && A[sameAIdx]!=B[bIdx]) {
                    sameAIdx++;
                }

                if (sameAIdx<=sameBIdx) {
                    aIdx=sameAIdx;
                    if (aIdx<N) {
                        commonNums.add(A[aIdx]);
                    }
                    aIdx++;
                } else {
                    bIdx=sameBIdx;
                    if (bIdx<M) {
                        commonNums.add(B[bIdx]);
                    }
                    bIdx++;
                }
            }
        }

        List<Number> nums = new ArrayList<>();
        for (int i = 0; i < commonNums.size(); i++) {
            nums.add(new Number(i, commonNums.get(i)));
        }

        Collections.sort(nums);

        int idx = 0;
        List<Number> res = new ArrayList<>();
        for (Number n : nums) {
            if (idx <= n.index) {
                idx = n.index;
                res.add(n);
            }
        }

        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i).value + " ");
        }
    }

    private static class Number implements Comparable<Number> {
        int index, value;

        public Number(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Number o) {
            return this.value != o.value ? o.value - this.value : this.index - o.index;
        }
    }

}
