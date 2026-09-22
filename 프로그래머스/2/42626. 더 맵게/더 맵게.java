import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int mix = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (a-b));
        
        Arrays.sort(scoville);
        for (int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            if (cur >= K) return mix;  // 현재 음식 스코빌지수가 K 이상이면 즉시 섞은 횟수를 반환
            
            // 현재 음식 스코빌지수가 K 미만이면 다음 음식이랑 섞고 우선순위 큐에 넣기
            if (!pq.isEmpty()) {
                int next = pq.poll();  
                pq.offer(cur+next*2);
                mix+=1;
            }
        }
        
        // pq 다 도는 동안 스코빌지수가 K 이상인 음식이 없었을 경우는 -1 return
        return -1;
    }
}