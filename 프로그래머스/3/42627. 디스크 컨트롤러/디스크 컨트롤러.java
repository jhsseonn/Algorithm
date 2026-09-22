import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Wait> pq = new PriorityQueue<>();
        
        int idx = 0;
        int spendTime = 0;
        int returnTime = 0;
        
        Arrays.sort(jobs, (a, b) -> a[0]-b[0]);
        
        while (idx<jobs.length || !pq.isEmpty()) {
            // 현재 시간까지 요청된 작업들 전부 큐에 넣음
            while (idx < jobs.length && jobs[idx][0]<=spendTime) {
                pq.offer(new Wait(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            // 처리할 작업이 없으면 다음 작업 요청 시간으로 이동
            if (pq.isEmpty()) {
                spendTime = jobs[idx][0];
                continue;
            }
            
            Wait cur = pq.poll();
            spendTime+=cur.spend;  // 작업 종료 시각
            returnTime+=spendTime-cur.req;  // 작업 반환 시간
        }
        
        return returnTime/jobs.length;
    }
}

class Wait implements Comparable<Wait> {
    int req;
    int spend;
    
    public Wait (int req, int spend) {
        this.req=req;
        this.spend=spend;
    }
    
    @Override
    public int compareTo(Wait o) {
        return this.spend!=o.spend ? this.spend-o.spend : this.req-o.req;  // 우선순위: 소요 시간 -> 요청 시간
    }
}