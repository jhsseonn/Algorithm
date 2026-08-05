import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    int n, m;
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        bfs(maps, 0, 0);
        
        if (maps[n-1][m-1]==1) {
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
                
                if (nx < 0 || nx >= n || ny <0 || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny]==0) {
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