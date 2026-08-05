import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};  // 동서
    int[] dy = {0, 0, -1, 1};  // 남북
    boolean[][] visited;
    int n, m;
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        bfs(maps, 0, 0);
        
        if (maps[n-1][m-1]==1) {  // 상대 팀 진영으로 가는 길이 막혀있는 경우
            answer = -1;
        } else answer = maps[n-1][m-1];
        
        return answer;
    }
    
    private void bfs(int[][] maps, int x, int y) {
        Deque<Pos> dq = new ArrayDeque<>();
        dq.offer(new Pos(x, y));
        visited[x][y] = true;
        
        while(!dq.isEmpty()) {
            Pos cur = dq.poll();
            
            for (int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                
                if (nx < 0 || nx >= n || ny <0 || ny >= m) continue;  // 범위 밖
                if (visited[nx][ny]) continue;  // 이미 방문한 노드는 계산하지 않는다
                if (maps[nx][ny]==0) {  // 벽
                    continue;
                } else {
                    maps[nx][ny] = maps[cur.x][cur.y]+1;
                    visited[nx][ny] = true;
                    dq.offer(new Pos(nx, ny));
                }
            }
        }
    }
}

class Pos {
    int x;
    int y;
    
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}