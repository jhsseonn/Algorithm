package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_모의SW_수영장_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int day, month, three, year;
	static int[] swimDays;
	static int minAmount;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			three = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			swimDays = new int[12];
			minAmount = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				swimDays[i] = Integer.parseInt(st.nextToken());
			}
			
			calculateAmount(0, 0);
			sb.append("#").append(test_case).append(" ").append(minAmount).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void calculateAmount(int depth, int total) {
		if (total>=minAmount) {
			return;
		}
		if (depth>=12) {
			minAmount = total;
			minAmount = Math.min(minAmount, year);
			return;
		}
		
		calculateAmount(depth+1, Math.min(total+month, total+swimDays[depth]*day));
		calculateAmount(depth+3, total+three);
	}

}
