package BOJ.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 121,236kb / 628ms
 */
public class Main_12015_G2_가장_긴_증가하는_부분_수열_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int num:arr) {
            int pos = binarySearch(num);
            if (pos==lis.size()) lis.add(num);
            else lis.set(pos, num);
        }

        System.out.println(lis.size());
    }

    private static int binarySearch(int key) {
        int start = 0, end = lis.size()-1;

        while(start<=end) {
            int mid = start + (end-start)/2;
            if (lis.get(mid)<key) start = mid+1;
            else end = mid-1;
        }

        return start;
    }
}
