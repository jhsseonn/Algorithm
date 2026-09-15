import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        // words에 target이 없으면 0 
        if (!Arrays.asList(words).contains(target)) return 0;
        
        // 단계 돌면서 카운트
        bfs(begin, target, words);
        
        return min;
    }
    
    private void bfs(String begin, String target, String[] words) {
        Deque<Level> dq = new ArrayDeque<>();
        dq.offer(new Level(0, begin));
        visited = new boolean[words.length];
        
        while(!dq.isEmpty()) {
            Level now = dq.poll();
            String cur = now.word;
            
            if (cur.equals(target)) {  // 타겟에 도착
                min = Math.min(min, now.idx);
                continue;
            }
            
            for (int i=0; i<words.length; i++) {
                if (visited[i]) continue;  // 이미 방문한 경우 재방문하지 않는다
                int cnt = 0;
                for (int j=0; j<cur.length(); j++) {
                    if (words[i].charAt(j)!=cur.charAt(j)) cnt+=1;
                }
                if (cnt==1) {  // 하나만 다를 경우 
                    dq.offer(new Level(now.idx+1, words[i]));
                    visited[i] = true;
                }
            }
        }
    }
}

class Level {
    int idx;
    String word;
    
    public Level(int idx, String word) {
        this.idx = idx;
        this.word = word;
    }
}