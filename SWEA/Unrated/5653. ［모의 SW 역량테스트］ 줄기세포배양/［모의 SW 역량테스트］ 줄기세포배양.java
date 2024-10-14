import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
	static int[] dx = new int[] {-1,1,0,0};
    static int[] dy = new int[] {0,0,-1,1};
    static int N, M, K;
    static PriorityQueue<Cell> pq;
    static Set<String> set;
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case=1; test_case<=T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            set = new HashSet<>();
            pq = new PriorityQueue<>((cell1, cell2) ->  {
                return cell2.x - cell1.x;
            });
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x!=0) {
                    	Cell cell = new Cell(i, j, false, x, 0);
                    	set.add(i+","+j);
                    	pq.add(cell);
                    }
                }
            }
            
            updateCell();
            
            sb.append("#").append(test_case).append(" ").append(pq.size()).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void updateCell() {
    	// 시간마다 업데이트
        for(int i=1; i<K+1; i++) {
            PriorityQueue<Cell> pq2 = new PriorityQueue<>((cell1, cell2) ->  {
                return cell2.x - cell1.x;
            });
            
            while(!pq.isEmpty()) {
                Cell now = pq.poll();
                
                // 죽은 경우 비활성화
                if (i-now.time >= 2*now.x) {
                	now.isActivated = false;
                	continue;
                }
                
                
                // 활성화 전 비활성화
                if (i-now.time < now.x) {
                	pq2.add(now);
                	continue;
                }
                
                // 활성화한다
                now.isActivated = true;
                pq2.add(now);
                
                if (i==K) continue; //마지막 K 시간일 때는 번식하지 않는다
                
                for(int k=0; k<4; k++) {
                    int nextX = now.r + dx[k];
                    int nextY = now.c + dy[k];
                    
                    // 좌표 비교 (set에 해당 좌표가 이미 존재하면 건너뛰기)
                    if (set.contains(nextX+","+nextY)) continue;
                    
                    // 세포 좌표 pq2에 넣기
                    set.add(nextX+","+nextY);
                    pq2.add(new Cell(nextX, nextY, false, now.x, i+1));
                }
            }
            pq = pq2;  // 업데이트 결과 저장
        }
    }
    
    private static class Cell{
        int r, c;
        boolean isActivated;
        int x;    //life
        int time;  // 생성 시간
        
        public Cell(int r, int c, boolean isActivated, int x, int time) {
            this.r = r;
            this.c = c;
            this.isActivated = isActivated;
            this.x = x;
            this.time = time;
        }
    }

}