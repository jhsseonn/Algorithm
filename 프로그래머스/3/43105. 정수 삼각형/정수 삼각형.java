import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        int dp[][] = new int[len][len];
        
        for (int i=0; i<len; i++) {
            for (int j=0; j<i+1; j++) {
                dp[i][j] = triangle[i][j];  //dp 초기화
            }
        }
        
        for (int i=1; i<len; i++) {
            for (int j=0; j<i+1; j++) {
                if (j==0) {
                    dp[i][j] += dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j]+dp[i-1][j-1], dp[i][j]+dp[i-1][j]);
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
}