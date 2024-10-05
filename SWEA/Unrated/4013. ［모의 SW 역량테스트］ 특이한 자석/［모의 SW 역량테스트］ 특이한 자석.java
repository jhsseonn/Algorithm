import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Integer>[] magnet;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int K = Integer.parseInt(br.readLine());
            magnet = new ArrayList[4];

            for (int i = 0; i < 4; i++) {
                magnet[i] = new ArrayList<>();
            }

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            // k번만큼 자석 움직이기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                move(m-1, d);
            }

            sb.append("#").append(test_case).append(" ").append(getResult()).append("\n");
        }
        System.out.println(sb);
    }

    private static int getResult(){
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (magnet[i].get(0) == 1) {
                result += (1 << i); // 1, 2, 4, 8으로 더함
            }
        }
        return result;
    }

    // 자석 움직이기
    private static void move(int m, int dir) {
        int[] dirs = new int[4];
        dirs[m] = dir;

        // 왼쪽 확인
        for (int i = m; i > 0; i--) {
            if (magnet[i].get(6) != magnet[i-1].get(2)) {
                dirs[i-1] = -dirs[i];
            } else {
                break;
            }
        }

        // 오른쪽 확인
        for (int i = m; i < 3; i++) {
            if (magnet[i].get(2) != magnet[i+1].get(6)) {
                dirs[i+1] = -dirs[i];
            } else {
                break;
            }
        }

        // 회전 적용
        rotate(dirs);
    }

    private static void rotate(int[] dirs) {
        for (int i = 0; i < 4; i++) {
            if (dirs[i] == 1) {
                int tmp = magnet[i].remove(magnet[i].size() - 1);
                magnet[i].add(0, tmp);
            } else if (dirs[i] == -1) {
                int tmp = magnet[i].remove(0);
                magnet[i].add(tmp);
            }
        }
    }
}
