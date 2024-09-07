package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_G3_세_용액 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] solvent = new int[N];
        int minSum = Integer.MAX_VALUE;
        int ansS, ansE, ansM;
        ansS = ansE = ansM = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solvent[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solvent);
        int start = 0;
        int end = solvent.length-1;

        while(start!=end) {
            int mid = (start+end)/2;
            // start와 end 사이 범위 내에서 0과 가장 가까운 수 찾기


            int sum = solvent[start]+solvent[end]+solvent[mid];
            if (minSum>Math.abs(sum)) {
                minSum = Math.abs(sum);
                ansS = start;
                ansE = end;
                ansM = mid;
            }
            if (sum<0) start+=1;
            if (sum>0) end-=1;
            if (sum==0) break;
        }

        int[] res = new int[] {solvent[ansS], solvent[ansM], solvent[ansE]};
        Arrays.sort(res);

        for (int a:res) {
            System.out.print(a+" ");
        }
    }
}
