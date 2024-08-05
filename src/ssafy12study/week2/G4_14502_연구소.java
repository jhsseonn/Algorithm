package ssafy12study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_14502_연구소 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int maxArea = 0;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static int[][] lab;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxArea);
    }

    private static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void dfs(int count) {
        if (count==3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j]==0) {
                    lab[i][j]=1;
                    dfs(count+1);
                    lab[i][j]=0;
                }
            }
        }
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j]==2) {
                    q.add(new Node(i, j));
                }
            }
        }

        int[][] copyLab = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyLab[i] = lab[i].clone();
        }

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];
                if (nx >= 0 && nx < N && ny >=0 && ny < M) {
                    if (copyLab[nx][ny]==0) {
                        q.add(new Node(nx, ny));
                        copyLab[nx][ny]=2;
                    }
                }
            }
        }

        safetyZone(copyLab);
    }

    private static void safetyZone(int[][] lab) {
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j]==0) {
                    area++;
                }
            }
        }
        if (maxArea < area) maxArea = area;
    }
}
