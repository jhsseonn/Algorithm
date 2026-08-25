import java.util.*;

class Solution {
    boolean[] visited;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        permutation(dungeons, k, 0);
        
        return answer;
    }
    
    private void permutation(int[][] dungeons, int curK, int cnt) {
        answer = Math.max(answer, cnt);
        
        if (curK==0) return;
        
        for(int i=0; i<dungeons.length; i++) {
            if (visited[i]) continue;  // 방문한 적 있으면 건너뛴다
            if (curK < dungeons[i][0] || curK-dungeons[i][1]<0) continue;  // 현재 피로도가 최소 필요도 보다 작거나 현재 피로도에서 소모 피로도를 뺐을 때 0보다 작은 경우 이 던전은 돌 수 없음
            visited[i] = true;
            permutation(dungeons, curK-dungeons[i][1], cnt+1);
            visited[i] = false;
        }
    }
}