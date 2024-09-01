package liveForm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * @author THKim
 */
/*
7
8
0 1
0 2	
1 3
1 4
2 4
3 5
4 5
5 6	
*/
public class AdjList_dfs {

	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}
	static int N;
	static Node[] adjList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		N = sc.nextInt(); // 정점수
		int C = sc.nextInt(); // 간선수
		
		adjList = new Node[N];
	
		//정점 입력 받기 
		
	}
	
	static String input="7\r\n" + 
			"8\r\n" + 
			"0 1\r\n" + 
			"0 2	\r\n" + 
			"1 3\r\n" + 
			"1 4\r\n" + 
			"2 4\r\n" + 
			"3 5\r\n" + 
			"4 5\r\n" + 
			"5 6	";
}
