package ssafy12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FloydTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        final int MAX = Integer.MAX_VALUE>>2;

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i!=j && map[i][j]==0) {
                    map[i][j] = MAX;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i==k) continue;
                for (int j = 0; j < N; j++) {
                    if (i==j || k==j) continue;

                    if (map[i][j]>map[i][k]+map[k][j]) {
                        map[i][j] = map[i][k]+map[k][j];
                    }
                }
            }
        }
    }
}
