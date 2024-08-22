package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_모의SW_보호_필름_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int result;
	static int D, W, K;
	static int[] isChecked;
	static int[][] board, boardCopy;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			result = Integer.MAX_VALUE;
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[D][W];
			boardCopy = new int[D][W];
			isChecked = new int[D];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				boardCopy[i] = board[i].clone();
			}

			Arrays.fill(isChecked, -1);
			getSubset(0, 0);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void getSubset(int depth, int len) {
		if (depth==D) {
			if (len>=result) return;
			for (int i = 0; i < D; i++) {
				boardCopy[i] = board[i].clone();
				if (isChecked[i]!=-1) changeFilm(i, isChecked[i]);
			}
			if (checkFilm()) result = Math.min(result, len);
			return;
		}
		
		//A
		isChecked[depth] = 0;
		getSubset(depth+1, len+1);
		
		//B
		isChecked[depth] = 1;
		getSubset(depth+1, len+1);
		
		//넣지 않는 경우
		isChecked[depth] = -1;
		getSubset(depth+1, len);
	}
	
	private static void changeFilm(int filmIdx, int value) {
		for (int i = 0; i < W; i++) {
			boardCopy[filmIdx][i] = value;
		}
	}
	
	private static boolean checkFilm() {
		int cnt, now;
		for (int i = 0; i < W; i++) {
			boolean pass = false;
			now = boardCopy[0][i];
			cnt = 1;

			for (int j = 1; j < D; j++) {
				if (boardCopy[j][i]==now) {
					cnt+=1;
				} else {
					now = boardCopy[j][i];
					cnt=1;
				}
				if (cnt>=K) {
					pass=true;
					break;
				}
			}

			if (!pass) return false;
		}
		return true;
	}
}
