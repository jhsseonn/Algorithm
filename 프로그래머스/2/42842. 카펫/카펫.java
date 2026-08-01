class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int carpetCnt = brown+yellow;
        
        for (int i=3; i<=carpetCnt/i; i++) {
            if (carpetCnt%i==0) {
                int row = i;
                int col = carpetCnt/i;
                
                int cYellow = (row-2) * (col-2);
                int cBrown = carpetCnt - cYellow;
                
                if ((yellow==cYellow) && (brown==cBrown)) {
                    answer[0] = col;
                    answer[1] = row;
                }
            }
        }
        
        return answer;
    }
}