import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int min = Integer.MAX_VALUE;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] positions = new int[102][102];
        visited = new boolean[102][102];  // 짜피 좌표 1~50까지니까 최대로 만들어놓기
        
        for (int[] r:rectangle) {  // 사각형 영역 전부 1로 채우기
            int leftX = r[0]*2;
            int leftY = r[1]*2;
            int rightX = r[2]*2;
            int rightY = r[3]*2;
            
            for (int x=leftX; x<=rightX; x++) {
                for (int y=leftY; y<=rightY; y++) {
                    positions[x][y] = 1;
                }
            }
        }
        
        for (int[] r:rectangle) {  // 사각형 내부 영역 0으로 비우기
            int leftX = r[0]*2;
            int leftY = r[1]*2;
            int rightX = r[2]*2;
            int rightY = r[3]*2;
            
            for (int x=leftX+1; x<rightX; x++) {
                for (int y=leftY+1; y<rightY; y++) {
                    positions[x][y] = 0;
                }
            }
        }
        
        bfs(positions, new Pos(characterX*2, characterY*2, 0), new Pos(itemX*2, itemY*2, 0));
        
        return min;
    }
    
    private void bfs(int[][] positions, Pos character, Pos item) {
        Deque<Pos> dq = new ArrayDeque<>();
        dq.offer(character);
        visited[character.x][character.y] = true;  // 캐릭터 시작 지점 
        
        while(!dq.isEmpty()) {
            Pos cur = dq.poll();
            
            if (cur.x == item.x && cur.y == item.y) {  // 아이템에 도달
                min = Math.min(min, cur.idx/2);
                continue;
            }
            
            for (int i=0; i<4; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                
                if (nx<0 || nx>=102 || ny<0 || ny>=102 || visited[nx][ny]) continue;
                
                if (positions[nx][ny]==1) {
                    visited[nx][ny]=true;
                    dq.offer(new Pos(nx, ny, cur.idx+1));
                }
            }
        }
    }
}

class Pos {
    int x;
    int y;
    int idx;
    
    public Pos(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}