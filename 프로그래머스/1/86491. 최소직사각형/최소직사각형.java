import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int len = sizes.length;
        int lMax = 0;
        int rMax = 0;
        
        for (int i=0; i<len; i++) {
            Arrays.sort(sizes[i]);
            lMax = Math.max(lMax, sizes[i][0]);
            rMax = Math.max(rMax, sizes[i][1]);
        } 
        
        return lMax*rMax;
    }
}