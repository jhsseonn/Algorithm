package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17135_G3_캐슬_디펜스_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] castle;
    static int[][] castleCopy;
    static int N;
    static int M;
    static int D;
    static int[] defences = new int[3];
    static List<int[]> defencesList = new ArrayList<>();
    static List<Enemy> enemies = new ArrayList<>();
    static int maxKilled = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        castle = new int[N + 1][M];
        castleCopy = new int[N+1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 배치
        getDefence(0, 0);

        for (int[] dArray : defencesList) {
            // 게임 초기 데이터로 초기화
            getData();
            // 적의 위치 리스트로 받기 - 초기
            getEnemies();
            // 공격
            maxKilled = Math.max(maxKilled, kill(dArray));
        }

        System.out.println(maxKilled);
    }

    private static void getData() {
        for (int i = 0; i < N; i++) {
            castleCopy[i] = castle[i].clone();
        }
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
                if (castleCopy[i][j] == 1) {
                    enemies.add(new Enemy(i, j));
                }
            }
        }
    }

    // 현재 선택된 궁수 조합으로 죽일 수 있는 최대 적의 수 반환
    private static int kill(int[] dArray) {
        int killed = 0;

        // 적이 모두 죽을 때까지 반복
        while(enemies.size()>0) {
            // 라운드 재개 전 초기화
            Set<Enemy> killEnemies = killTheEnemy(dArray);
            for (Enemy e : killEnemies) {
                if (castleCopy[e.x][e.y] ==0) {
                    continue;
                }
                castleCopy[e.x][e.y] = 0;
                killed += 1;
                for (int i = 0; i < enemies.size(); i++) {
                    if (e.x==enemies.get(i).x&&e.y==enemies.get(i).y){
                        enemies.remove(i);
                    }
                }
            }
            // 아직 죽지 않은 적의 위치 업데이트
            updateEnemies();
        }
        return killed;
    }

    private static Set<Enemy> killTheEnemy(int[] dArray) {
        Set<Enemy> killEnemies = new HashSet<>();
        for (int d : dArray) {
            int minDistance = D;
            Enemy minEnemy = null;
            for (Enemy e:enemies) {
                int distance = Math.abs(N - e.x) + Math.abs(d - e.y);
                if (distance > minDistance) continue;
                // distance <= minDistance일 경우 minEnemy 갱신
                if (distance < minDistance) {
                    minEnemy = e;
                    minDistance = distance;
                } else if (distance==minDistance) {
                    // 만약 첫 적의 정보인 경우 minEnemy와 위치 비교 없이 바로 갱신
                    if (minEnemy == null) {
                        minEnemy = e;
                    } else if (e.y < minEnemy.y) {
                        minEnemy = e;
                    }
                }
            }
            if (minEnemy!=null) killEnemies.add(minEnemy);
        }
        return killEnemies;
    }

    private static void updateEnemies() {
        if (enemies.size()==0) return;
        for (int i = 0; i< enemies.size(); i++) {
            int x = enemies.get(i).x;
            int y = enemies.get(i).y;
            castleCopy[x][y] = 0;
            if (x+1 == N) {
                enemies.remove(i);
                i--;
                continue;
            }
            enemies.get(i).setX(x+1);
            castleCopy[x+1][y] = 1;
        }
    }

    private static class Enemy {
        int x;
        int y;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
