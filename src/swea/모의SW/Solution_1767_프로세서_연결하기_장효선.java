package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 24,348kb / 시간: 202ms
 */
public class Solution_1767_프로세서_연결하기_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, minLen, maxCore;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> process;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T+1; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            minLen = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;
            process = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    // 가장자리에 위치한 코어는 이미 연결된 상태로 제외
                    if (arr[i][j]==1 && i!=0 && j!=0 && i!=N-1 && j!=N-1) {
                        process.add(new int[] {i, j});
                    }
                }
            }

            // 프로세서 연결하기
            dfs(0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(minLen).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int processIdx, int lenSum, int coreCnt) {
        // 모든 프로세스에 대한 탐색이 끝난 경우
        if (processIdx==process.size()) {
            // 연결된 코어의 개수가 더 많은 경우 무조건 갱신
            if (maxCore<coreCnt) {
                maxCore = coreCnt;
                minLen = lenSum;
            }
            // 연결된 코어의 개수가 같은 경우 전선의 길이가 더 짧은 경우를 갱신
            if (maxCore==coreCnt) {
                minLen = Math.min(minLen, lenSum);
            }
            return;
        }

        int r = process.get(processIdx)[0];
        int c = process.get(processIdx)[1];

        // 상하좌우로 전선 연결 시도
        for (int i = 0; i < 4; i++) {
            // 가장자리에 연결이 가능한 경우라면
            if (connect(r, c, i)) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                int sum = 0;

                // 전선 연결
                while(nr>=0 && nr<N && nc>=0 && nc<N) {
                    arr[nr][nc] = 1;
                    sum+=1;
                    nr += dr[i];
                    nc += dc[i];
                }

                dfs(processIdx+1, lenSum+sum, coreCnt+1);

                // 전선 연결 해제
                nr = r+dr[i];
                nc = c+dc[i];

                // 전선 연결
                while(nr>=0 && nr<N && nc>=0 && nc<N) {
                    // 중간에 전선 or core를 만나면 연결할 수 없음
                    arr[nr][nc] = 0;
                    nr += dr[i];
                    nc += dc[i];
                }
            }
        }
        dfs(processIdx+1, lenSum, coreCnt);
    }

    private static boolean connect(int r, int c, int dir) {
        int nr = r+dr[dir];
        int nc = c+dc[dir];
        while(nr>=0 && nr<N && nc>=0 && nc<N) {
            // 중간에 전선 or core를 만나면 연결할 수 없음
            if (arr[nr][nc]==1) return false;
            nr += dr[dir];
            nc += dc[dir];
        }
        return true;
    }
}
