import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, min, max;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        boolean color = true;
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                char c = input.charAt(j-1);
                if (c=='B' && color) {
                    board[i][j]=1;
                } else if (c=='W' && !color) {
                    board[i][j]=1;
                }
                color=!color;
            }
            if (M%2==0) color=!color;
        }

        for (int i = 1; i <=N ; i++) {
            int temp = board[i][1];
            for (int j = 2; j <=M ; j++) {
                temp += board[i][j];
                board[i][j] = temp;
            }
        }

        for (int i = 1; i <=M ; i++) {
            int temp = board[1][i];
            for (int j = 2; j <=N ; j++) {
                temp += board[j][i];
                board[j][i] = temp;
            }
        }

        for (int i = K; i <= N ; i++) {
            for (int j = K; j <=M ; j++) {
                int temp = board[i][j] - (board[i-K][j]+board[i][j-K]- board[i-K][j-K]);
                min = Math.min(min, temp);
                max = Math.max(max, temp);
            }
        }

        int ans = Math.min(min, K*K-max);

        System.out.println(ans);
    }
}
