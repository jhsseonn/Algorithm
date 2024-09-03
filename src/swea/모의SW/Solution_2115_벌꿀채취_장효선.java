package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 메모리: 22,548kb / 시간: 165ms
 *
 */
public class Solution_2115_벌꿀채취_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, C, maxProfit, ans;
    static int[][] honeyComb;
    static List<Honey> honeys;
    static int[] values;
    static boolean[] selected, selectedHoney;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            honeyComb = new int[N][N];
            honeys = new ArrayList<>();

            // 입력받기 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    honeyComb[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            makeCases();

            sb.append("#").append(test_case).append(" ").append(getResult()).append("\n");
        }
        System.out.println(sb);
    }

    // 모든 M개의 통의 경우의 수 구하기
    private static void makeCases() {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-M+1; j++) {
                // M개의 벌통에서 합이 C를 넘지 않으면서 제곱수의 합이 최대인 부분집합 구하기
                values = new int[M];
                selected = new boolean[M];
                maxProfit = Integer.MIN_VALUE;
                for (int k = j; k < j+M; k++) {
                    values[k-j] = honeyComb[i][k];
                }
                getMaxProfit(0, 0, 0);
                honeys.add(new Honey(i, j, j+M-1, maxProfit));
            }
        }
    }

    // 선택한 벌통의 최선의 값 구하기
    private static void getMaxProfit(int idx, int cnt, int sum) {
        if (sum>C) return;
        if (idx==M && sum<=C) {
            int res = 0;
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    res+=values[i]*values[i];
                }
            }
            maxProfit = Math.max(maxProfit, res);
            return;
        }

        selected[idx] = true;
        getMaxProfit(idx+1, cnt+1, sum+values[idx]);
        selected[idx] = false;
        getMaxProfit(idx+1, cnt, sum);
    }

    // M개의 통의 경우의 수 중 최선의 값 2개 뽑기
    private static int getResult() {
        Collections.sort(honeys);
        selectedHoney = new boolean[honeys.size()];
        maxProfit = Integer.MIN_VALUE;
        
        perm(0);
        
        return maxProfit;
    }
    
    private static void perm(int depth) {
    	if (depth==2) {
    		Honey[] select = new Honey[2];
    		int idx = 0;
    		for (int i = 0; i < selectedHoney.length; i++) {
				if (selectedHoney[i] && idx<2) {
					select[idx] = honeys.get(i);
					idx+=1;
				}
			}
    		
    		Honey honey1 = select[0];
    		Honey honey2 = select[1];
    		
    		if (honey1.r == honey2.r) {
    			if (honey1.startC<=honey2.startC && honey1.endC >= honey2.startC) return;
    			if (honey1.startC>=honey2.startC && honey1.startC <= honey2.endC) return;
    		}
    		
    		maxProfit = Math.max(maxProfit, honey1.profit+honey2.profit);
    		return;
    	}
    	
    	if (maxProfit!=Integer.MIN_VALUE) return;
    	
    	for (int i = 0; i < honeys.size(); i++) {
			if (selectedHoney[i]) continue;
			selectedHoney[i] = true;
			perm(depth+1);
			selectedHoney[i] = false;
		}
    }

    private static class Honey implements Comparable<Honey> {
        int r, startC, endC, profit;

        public Honey(final int r, final int startC, final int endC, final int profit) {
            this.r = r;
            this.startC = startC;
            this.endC = endC;
            this.profit = profit;
        }

        @Override
        public int compareTo(final Honey o) {
            return o.profit - this.profit;
        }
    }
}
