package liveForm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TopologicalSortTest_API {
	public static void main(String[] args) throws Exception{
		//입력 받기 
		BufferedReader in = new BufferedReader(new StringReader(input));
		StringTokenizer st= new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());		//정점 개수
		int E = Integer.parseInt(st.nextToken());		//간선 개수
		
		//진출을 담을 배열 선언
		ArrayList<Integer>[] list = new ArrayList[N+1];
		
		//진입 차수를 위한 배열 선언
		int[] inDegree = new int[N+1];
		
		//위상 정렬한 결과를 담을 리스트 
		ArrayList<Integer> result = new ArrayList<>();
		
		//진입 차수가 0인 노드를 담을 큐를 선언 
		LinkedList<Integer> q= new LinkedList<>();
		
		for (int i =1; i <=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		//간선 정보 입력 받기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			inDegree[to]++;
		}

		for (int i = 1; i <= N; i++) {
			if (inDegree[i]==0) q.add(i);
		}

		while(!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);

			for (int n:list[cur]) {
				if (--inDegree[n]==0) {
					q.add(n);
				}
			}
		}
		
		//3. 결과 출력
		int size = result.size();
		if(size == N) {
			for (int i = 0; i < size; i++) {
				System.out.print(result.get(i)+" ");
			}
		}else {
			System.out.println("cycle");
		}
	}
	static String input = "8 9 \r\n" + 
			"1 2\r\n" + 
			"1 3\r\n" + 
			"1 4\r\n" + 
			"4 5\r\n" + 
			"5 6\r\n" + 
			"3 6\r\n" + 
			"3 7\r\n" + 
			"2 7\r\n" + 
			"7 8";
}









