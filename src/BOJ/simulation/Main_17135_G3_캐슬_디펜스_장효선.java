package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17135_G3_캐슬_디펜스_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] castle;
    static int N;
    static int M;
    static int D;
    static int[] defences = new int[3];
    static List<int[]> defencesList = new ArrayList<>();
    static List<int[]> enemies = new ArrayList<>();
    static List<int[]> killEnemies;
    static int maxKilled = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        castle = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 배치
        getDefence(0, 0);
        // 적의 위치 리스트로 받기 - 초기
        getEnemies();

        for (int[] dArray : defencesList) {
            // 공격
            maxKilled = Math.max(maxKilled, kill(dArray));
        }

        System.out.println(maxKilled);
    }

    private static void getDefence(int depth, int start) {
        if (depth == 3) {
            defencesList.add(new int[]{defences[0], defences[1], defences[2]});
            return;
        }
        for (int i = start; i < M; i++) {
            defences[depth] = i;
            getDefence(depth + 1, i + 1);
        }
    }

    private static void getEnemies() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (castle[i][j] == 1) {
                    enemies.add(new int[]{i, j});
                }
            }
        }
    }

    private static int kill(int[] dArray) {
        int killed = 0;
        for (int i = 0; i < M; i++) {
            // 라운드 재개 전 초기화
            killEnemies = new ArrayList<>();
            if (enemies.size() == 0) break;
            for (int d : dArray) {
                killTheEnemy(d);
            }
            for (int[] e : killEnemies) {
//				System.out.println(Arrays.toString(e));
                if (castle[e[0]][e[1]] != 1) {
                    continue;
                }
                castle[e[0]][e[1]] = 0;
                killed += 1;
            }
            // 적의 위치 업데이트
            updateEnemies();
        }
        return killed;
    }

    // 로직 다시 짜기
    private static void killTheEnemy(int d) {
        int minDistance;
        int[] minEnemy = new int[2];
        for (int[] e : enemies) {
            minDistance = D;
            int distance = Math.abs(M - e[0]) + Math.abs(d - e[1]);
            if (distance < minDistance) {
                minEnemy[0] = e[0];
                minEnemy[1] = e[1];
                minDistance = distance;
            }
//			if (distance==minDistance) {
//				if (minEnemy[0]==-1) {
//					minEnemy[0] = e[0];
//					minEnemy[1] = e[1];
//					minDistance = distance;
//				} else if (e[0]<minEnemy[0]){
//					minEnemy[0] = e[0];
//					minEnemy[1] = e[1];
//					minDistance = distance;
//				}
//			}


//            if (distance == minDistance) {
//                if (e[0])
//                    minEnemy[0] = e[0];
//                minEnemy[1] = e[1];
//            }
        }
        killEnemies.add(new int[]{minEnemy[0], minEnemy[1]});
    }

    private static void updateEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            int[] e = enemies.get(i);
            e[0] += 1;
            if (e[0] == M) {
                enemies.remove(i);
                continue;
            }
            castle[e[0]][e[1]] = 1;
        }
    }

}
