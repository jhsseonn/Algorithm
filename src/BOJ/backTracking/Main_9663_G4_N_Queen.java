package BOJ.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 행에 퀸을 두지 않는 방식 - 열 번호로 노드 관리
// 
public class Main_9663_G4_N_Queen {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, col[], ans;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		col = new int[N+1];
		
		setQueens(1);
		
		System.out.println(ans);
	}
	
	private static void setQueens(int rowNo) {
		// 가지치기
		if (!isAvailable(rowNo-1)) return;
		
		// leaf node 도달 시 answer+1
		if (rowNo > N) {
			ans++;
			return;
		}
		
		// 유도파트
		for (int i = 1; i < N+1; i++) {
			col[rowNo] = i;
			setQueens(rowNo+1);
		}
	}
	
	private static boolean isAvailable(int rowNo) {
		// 백트래킹
		// 이전 노드와 같은 행, 열, 대각선에 위치한 경우 해가 될 수 없음
		for (int i = 1; i < rowNo; i++) {
			if (col[rowNo] == col[i] || rowNo-i == Math.abs(col[rowNo]-col[i])) return false;
		}
		
		return true;
	}
}
