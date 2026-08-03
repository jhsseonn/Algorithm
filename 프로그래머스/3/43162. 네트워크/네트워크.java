class Solution {
    int count = 0;
    int max;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        max = n;
        visited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!visited[i]) {  // 이전에 방문하지 않았을 경우
                visited[i] = true;
                dfs(computers, i);
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(int[][] computers, int i) {
        if (i==max) {
            return;  // 범위 밖
        }
        
        visited[i]=true;
        
        for (int next=0; next<max; next++) {
            if (computers[i][next]==1 && !visited[next]) {  // 연결되어 있는 컴퓨터면 다음 컴퓨터와 연결된 컴퓨터 탐색
                dfs(computers, next);
            }
        }
    }
}