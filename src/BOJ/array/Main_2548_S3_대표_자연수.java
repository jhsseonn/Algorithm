package BOJ.array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 19,160kb / 336ms
 */
public class Main_2548_S3_대표_자연수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] arr;
    static Number[] nums;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        nums = new Number[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int dis = 0;

            for (int j = 0; j < N; j++) {
                dis += Math.abs(num-arr[j]);
            }

            nums[i] = new Number(num, dis);
        }

        Arrays.sort(nums);

        System.out.println(nums[0].num);
    }

    static class Number implements Comparable<Number> {
        int num, dis;

        public Number(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }

        @Override
        public int compareTo(Number o) {
            return this.dis == o.dis ? this.num - o.num : this.dis - o.dis;
        }
    }
}
