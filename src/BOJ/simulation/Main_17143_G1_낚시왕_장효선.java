package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17143_G1_낚시왕_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int R, C, M, ans;
	static int[][] arr;
	static List<Shark> sharks = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(r-1, c-1, s, d, z));
			// 상어가 있는 칸은 상어의 크기로 초기화
			arr[r-1][c-1] = z; 
		}
		
		for (int i = 0; i < C; i++) {
			fishing(i);
		}
		
		System.out.println(ans);
	}
	
	
	private static void fishing(int c) {
		// 제일 가까운 상어를 잡음
		for (int i = 0; i < R; i++) {
			if (arr[i][c]!=0) {
				System.out.println("round: "+c+" get: "+arr[i][c]);
				ans+=arr[i][c];  // 상어 잡음
				arr[i][c] = 0;  // 잡은 곳 초기화
				removeShark(i, c);
				break;
			}
		}
		
		// 상어들이 움직임
		moveShark();
	}
	
	private static void removeShark(int r, int c) {
		for (int i = 0; i < sharks.size(); i++) {
			if (sharks.get(i).r ==r && sharks.get(i).c == c) sharks.remove(i);
		}
	}
	
	private static void moveShark() {
		// 상어 리스트를 돌면서
		for (int i = 0; i<sharks.size(); i++) {
			Shark sk = sharks.get(i);
			int r = sk.r;
			int c = sk.c;
			int s = sk.s;
			int d = sk.d;
			
			int nr = r;
			int nc = c;
			// 상어의 위치와 방향을 계산한다
			if (d==1 || d==2) {
				s -= 2*(R-1);  // 속력의 나머지, 여기까지 방향은 그대로
				if (d==2 && r+s >= R) {  // 배열의 범위를 초과한 경우
					nr = 2*(R-1)-(r+s);
					d = 1;
				} else if (d==1 && r-s < 0) {
					nr = s-r;
					d = 2;
				} 
			}
			
			if (d==3 || d==4) {
				s -= 2*(C-1);  // 속력의 나머지, 여기까지 방향은 그대로
				if (d==3 && c+s >= C) {  // 배열의 범위를 초과한 경우
					nc = 2*(C-1)-(c+s);
					d = 4;
				} else if (d==4 && c-s < 0) {
					nc = s-c;
					d = 3;
				}
			}
			
			// 해당 칸으로 상어 이동(기존 칸은 0으로 초기화)
			arr[r][c] = 0;
			// 상어의 위치 갱신
			sk.r = nr;
			sk.c = nc;
			
			// 만약 해당 칸에 이미 상어가 존재한다면, 크기가 더 큰 경우에만 갱신
			if (arr[nr][nc]!=0) {
				arr[nr][nc] = Math.max(arr[nr][nc], sk.z);
			} else {
				arr[nr][nc] = sk.z;
			}
			
			// 크기 더 작으면 sharks에서 삭제(잡아먹힘)
			if (arr[nr][nc]!=sk.z) {
				sharks.remove(i);
				i--;
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
