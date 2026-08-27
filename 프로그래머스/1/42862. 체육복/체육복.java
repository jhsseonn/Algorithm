import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        boolean[] losts = new boolean[n+2];
        boolean[] reserves = new boolean[n+2];
        
        for (int i=0; i<lost.length; i++) {
            losts[lost[i]]=true;
        }
        
        for (int i=0; i<reserve.length; i++) {
            reserves[reserve[i]]=true;
        }
        
        boolean[] result = new boolean[n+2];
        for (int i=1; i<n+1; i++) {  
            if (!losts[i]) result[i]=true;
            if (losts[i] && reserves[i]) {
                result[i] = true;
                losts[i] = false;
                reserves[i] = false;
            }
        }
        
        for (int i=1; i<n+1; i++) {
            if (reserves[i]) {
                if (losts[i]) { // 여분이 있는데 잃어버렸고 result에 기록되지 않음
                    result[i]=true;
                    losts[i]=false;
                    continue;
                } else {  // 여분이 있고 잃어버리지 않음
                    if (losts[i-1]) {  // 앞 사람이 잃어버림
                        result[i-1]=true;
                        losts[i-1]=false;
                        continue;
                    } else if (losts[i+1]) {  // 뒷 사람이 잃어버림
                        result[i+1]=true;
                        losts[i+1]=false;
                        continue;
                    }
                }
            }
        }
        
        for (int i=1; i<n+1; i++) {
            if(result[i]) answer+=1;
        }
        
        return answer;
    }
}