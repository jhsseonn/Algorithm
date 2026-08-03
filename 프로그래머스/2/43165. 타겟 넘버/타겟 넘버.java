import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        
        bfs(numbers, target);
        
        return answer;
    }
    
    private void bfs(int[] numbers, int target) {
        Deque<NumSet> dq = new ArrayDeque<>();
        dq.offer(new NumSet(numbers[0], 0));
        dq.offer(new NumSet(-1*numbers[0], 0));
        
        while(true) {
            NumSet before = dq.poll();
            int sum = before.sum;
            int idx = before.idx;
            
            // 인덱스가 numbers 길이 보다 작은 경우에는 이전 값에서 계산한 값 넣어주기
            if (idx<numbers.length-1) {
                dq.offer(new NumSet(sum+numbers[idx+1],idx+1));
                dq.offer(new NumSet(sum-numbers[idx+1],idx+1));
            }
            
            if ((sum==target) && (idx==numbers.length-1)) {
                answer+=1;
            }
            
            if (dq.isEmpty()) break;
        }
    }
    
    class NumSet {
        int sum;
        int idx;

        public NumSet(int sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
    }
}
