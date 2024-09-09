package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1411_IM_두_줄로_타일_깔기_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];

		if(N==1) System.out.println(1);
		if(N==2) System.out.println(3);

		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i < N+1; i++) {
			dp[i] = (dp[i-1]+2*dp[i-2])%20100529;
		}
		
		System.out.println(dp[N]%20100529);
	}

}
