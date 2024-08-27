package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17143_G1_낚시왕_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int R, C, M, ans;
	static Shark[][] arr;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Shark[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			// 상어가 있는 칸 초기화
			arr[r-1][c-1] = new Shark(r-1, c-1, s, d, z);
		}
		
		for (int i = 0; i < C; i++) {
			fishing(i);
		}
		
		System.out.println(ans);
	}
	
	
	private static void fishing(int c) {
		// 제일 가까운 상어를 잡음
		for (int i = 0; i < R; i++) {
			if (arr[i][c]!=null) {
				ans+=arr[i][c].z;  // 상어 잡음
				arr[i][c] = null;  // 잡은 곳 초기화
				break;
			}
		}

		// 상어들이 움직임
		moveShark();
	}
	
	private static void moveShark() {
		Deque<Shark> dq = new ArrayDeque<>();
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, 1, -1};

		// 상어들 큐에 추가
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j]!=null) {
					dq.offer(new Shark(i, j, arr[i][j].s, arr[i][j].d, arr[i][j].z));
				}
			}
		}

		arr = new Shark[R][C];

		while(!dq.isEmpty()) {
			Shark sk = dq.poll();
			int r = sk.r;
			int c = sk.c;
			int s = sk.s;
			int d = sk.d;
			int z = sk.z;

			// 상어의 위치와 방향을 계산한다
			// 속력 최소화
			// 상, 하
			if (d==1 || d==2) {
				s =s% (2*(R-1));
			}

			// 우, 좌
			if (d==3 || d==4) {
				s = s% (2*(C-1));
			}

			for (int i = 0; i < s; i++) {
				int nr = r+dr[d-1];
				int nc = c+dc[d-1];

				if (nr<0 || nr>=R || nc<0 || nc >= C) { // 좌표가 범위 밖인 경우
					r -= dr[d-1];
					c -= dc[d-1];
					if (d==1 || d==3) d+=1;
					else d-=1;
					continue;
				}

				// 위치 벗어나지 않은 경우 새로운 위치로 이동
				r= nr;
				c = nc;
				d = d;
			}

			sk.r = r;
			sk.c = c;
			sk.d = d;
			
			// 만약 해당 칸에 이미 상어가 존재한다면, 크기가 더 큰 경우에만 갱신
			if (arr[r][c]!=null) {
				if (arr[r][c].z < z) {
					arr[r][c] = new Shark(r, c, s, d, z);
				}
			} else {
				arr[r][c] = new Shark(r, c, s, d, z);
			}
		}
	}

	private static class Shark {
		int r;
		int c;
		int s;
		int d;
		int z;
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
