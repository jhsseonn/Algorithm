package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리: 50,320KB / 시간: 372ms
 */
public class Main_1717_G5_집합의_표현 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        make();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command==0) union(a, b);
            if (command==1) {
                int aRoot = findSet(a);
                int bRoot = findSet(b);
                if (aRoot==bRoot) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    private static void make() {
        parents = new int[N+1];
        Arrays.fill(parents, -1);
    }

    private static int findSet(int a) {
        if (parents[a]<0) return a;
        return parents[a]=findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot==bRoot) return false;

        parents[aRoot] = bRoot;
        return true;
    }
}
