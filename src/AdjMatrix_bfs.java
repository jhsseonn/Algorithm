import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class AdjMatrix_bfs {
	static boolean map[][];
	static boolean visited[];	//노드에 대한 방문을 확인하는 배열 , 노드 개수 만큼 선언 
	static int N;
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			map=new boolean[N][N];
			visited=new boolean[N];   //모두 false로 초기화 되기 때문에 
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine()," ");
				for (int j = 0; j < N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
					}
				}
			}
			bfs(0);
			System.out.println();
		}
	}
	private static void bfs(int cur) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(cur);
		visited[cur] = true;
		
		/* 노드의 인접된 정보를 큐에 넣기 때문에 큐가 empty가 되는 상황은 모든 node를 방문한 상황  */
		while(!queue.isEmpty()) {
			cur = queue.poll();
			//해당 노드에 대한 처리 ...
			System.out.printf("%c->", cur+'A');
//			visited[cur] = true;
			
			//인접된 노드를 탐색
			for (int ad = 0; ad <N; ad++) {
//				인접이 됐고			방문한적이 없다면 큐에 넣기 
				if(map[cur][ad] && !visited[ad]) {
					queue.add(ad);
					visited[ad] = true;
				}
			}
		}
	}
}











