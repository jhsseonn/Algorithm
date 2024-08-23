package BOJ.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리: 16,304kb / 시간: 140ms
 */
public class Main_1194_G1_달이_차오른다_가자_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, startR, startC;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Character> keys = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
	static List<Character> doors = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][2<<6];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				// 민식이가 서 있는 위치를 bfs 시작 위치로 한다
				if (map[i][j]=='0') {
					startR = i;
					startC = j;
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Maze> q = new ArrayDeque<>();
		q.offer(new Maze(startR, startC, 0, 0));
		visited[startR][startC][0] = true;
		
		while(!q.isEmpty()) {
			Maze m = q.poll();
			int r = m.r;
			int c = m.c;
			int key = m.key;
			int distance = m.distance;
			
			// 출구면 탈출한다
			if (map[r][c]=='1') {
				return distance;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				
				if (nr<=-1 || nr>=N || nc<=-1 || nc>=M) continue;
				if (map[nr][nc]=='#') continue;

				// a, b, c, d, e, f 중 하나면 열쇠 정보를 받아준다
				int nk = keys.contains(map[nr][nc]) ? key | 1<<(map[nr][nc]-'a') : key;

				// A, B, C, D, E, F이면 현재 열쇠 정보를 확인한다(비트마스킹)
				if (doors.contains(map[nr][nc]) && (key & (1<<map[nr][nc]-'A'))==0) continue;

				if (visited[nr][nc][nk]) continue;
				
				// . 이면 이동한다
				visited[nr][nc][nk] = true;
				q.offer(new Maze(nr, nc, nk, distance+1));
			}
		}
		
		return -1;
	}
	
	private static class Maze {
		int r;
		int c;
		int key;
		int distance;
		
		public Maze(int r, int c, int key, int distance) {
			this.r = r;
			this.c = c;
			this.key = key;
			this.distance = distance;
		}
	}

}
