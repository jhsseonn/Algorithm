import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, X, result;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        result = 0;
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if (isValid(arr[i])) result++;
            if (isValid(getColumn(i))) result++;
        }

        System.out.println(result);
    }

    private static boolean isValid(int[] line) {
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N-1; i++) {
            if (line[i]==line[i+1]) continue;

            if (Math.abs(line[i]-line[i+1])>1) return false;

            if (line[i]-line[i+1]==1) {   // 내리막
                for (int j = 1; j <= X; j++) {
                    if (i+j >= N) return false;
                    if (line[i+j]!=line[i+1]) return false;
                    if (visited[i+j]) return false;
                    visited[i+j] = true;
                }
            } else if (line[i+1]-line[i]==1) {  // 오르막
                for (int j = 0; j < X; j++) {
                    if (i-j < 0) return false;
                    if (line[i-j]!=line[i]) return false;
                    if (visited[i-j]) return false;
                    visited[i-j] = true;
                }
            }
        }
        return true;
    }

    private static int[] getColumn(int col) {
        int[] column = new int[N];
        for (int i = 0; i < N; i++) {
            column[i] = arr[i][col];
        }

        return column;
    }
}
