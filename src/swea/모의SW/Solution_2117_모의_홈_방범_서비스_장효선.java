package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2117_모의_홈_방범_서비스_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, cost, ans, city[][];
    static boolean security[][];

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            city = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 운영비용이 0 이상일 동안 반복해서 집의 최댓값을 구한다
            K = 1;
            while (true) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        security = new boolean[N][N];
                        int cnt = 0;
                        if (city[i][j] == 1) {
                            security[i][j] = true;
                            cnt+=1;
                        }
                        // K 크기에 따른 집의 개수 계산
                        cnt += getHouseCnt(i, j);
                        cost = M * cnt - (K * K + (K - 1) * (K - 1));
                        if (cost >= 0) {
                            ans = Math.max(ans, cnt);
//                            System.out.println("K: "+K+" "+i+" "+j+" "+cnt+" "+cost);
                        }
                    }
                }
                if (K == N+1) break;
                K++;
            }

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int getHouseCnt(int i, int j) {
        int houseCnt = 0;
        int idx = 0;
        for (int c = j - K + 1; c <= j + K - 1; c++) {
            if (c < 0 || c >= N) {
                if (c < j) idx += 1;
                else idx -= 1;
                continue;
            }
            for (int r = i; r >= i - idx; r--) {
                if (r < 0 || r >= N) continue;
                if (city[r][c] == 1 && !security[r][c]) {
//                    System.out.println(r+" "+c);
                    houseCnt += 1;
                    security[r][c] = true;
                }
            }
            for (int r = i + 1; r <= i + idx; r++) {
                if (r < 0 || r >= N) continue;
                if (city[r][c] == 1 && !security[r][c]) {
//                    System.out.println(r+" "+c);
                    houseCnt += 1;
                    security[r][c] = true;
                }
            }
            if (c < j) idx += 1;
            else idx -= 1;
        }
        return houseCnt;
    }
}
