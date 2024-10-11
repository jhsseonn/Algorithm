import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static List<Atom> list;
    static int[][] arr = new int[4001][4001];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                x = (x+1000)*2;
                int y = Integer.parseInt(st.nextToken());
                y = (y+1000)*2;
                int v = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.add(new Atom(x, y, v, e));
                arr[x][y] = e;
            }

            sb.append("#").append(test_case).append(" ").append(getEnergy()).append("\n");
        }
        System.out.println(sb);
    }

    private static int getEnergy() {
        int ans = 0;

        while(!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Atom cur = list.get(i);
                int v = cur.v;
                int e = cur.e;

                arr[cur.x][cur.y] = 0;
                int nextX = cur.x+dx[v];
                int nextY = cur.y+dy[v];

                if (nextX > 4000 || nextX <0 || nextY > 4000 || nextY < 0) {
                    list.remove(i);
                    i--;
                    continue;
                }

                arr[nextX][nextY] +=e;
                cur.x = nextX;
                cur.y = nextY;
            }

            for (int i = 0; i < list.size(); i++) {
                Atom cur = list.get(i);
                if (arr[cur.x][cur.y]!=cur.e) {
                    ans+=arr[cur.x][cur.y];
                    arr[cur.x][cur.y] = 0;
                    list.remove(i);
                    i--;
                }
            }
        }
        return ans;
    }

    static class Atom {
        int x, y, v, e;

        public Atom(int x, int y, int v, int e) {
            this.x = x;
            this.y = y;
            this.v = v;
            this.e = e;
        }
    }
}
