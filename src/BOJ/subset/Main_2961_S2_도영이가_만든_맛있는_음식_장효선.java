package BOJ.subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 14,112KB / 시간: 100ms
 */
public class Main_2961_S2_도영이가_만든_맛있는_음식_장효선 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] inputSB;
	static int[][] subsetSB;
	static int minSB = 1000000000;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		inputSB = new int[N][2];
		subsetSB = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			inputSB[i][0] = Integer.parseInt(st.nextToken());
			inputSB[i][1] = Integer.parseInt(st.nextToken());
		}
		
		getSubset(0, 0);
		
		System.out.println(minSB);
	}
	
	private static void getSubset(int depth, int len) {
		if (depth==N) {
			if (subsetSB.length!=0) {  // 재료 적어도 하나 이상 사용해야 하므로 공집합은 제외
				evaluate(len);
			}
			return;
		}
		subsetSB[len][0] = inputSB[depth][0];
		subsetSB[len][1] = inputSB[depth][1];
		getSubset(depth+1, len+1);
		
		getSubset(depth+1, len);
	}
	
	private static void evaluate(int len) {
		int sour = 1;
		int bitter = 0;
		for (int i = 0; i < len; i++) {
			sour*=subsetSB[i][0];
			bitter+=subsetSB[i][1];
		}
		if (bitter > 0 && sour >1) {  // 재료
			minSB = Math.min(minSB, Math.abs(sour-bitter));
		}
	}

}
