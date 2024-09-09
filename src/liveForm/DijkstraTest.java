package liveForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DijkstraTest {

    //인접 리스트를 표현하기 위한 정점 정보
    static class Node{
        int vertex,weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            super();
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
        @Override
        public String toString() {
            return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(in.readLine().trim());
        int V = Integer.parseInt(st.nextToken()); //정점 갯수
        int E = Integer.parseInt(st.nextToken()); //간선 갯수
        st = new StringTokenizer(in.readLine().trim());
        int start = Integer.parseInt(st.nextToken());;
        int end =  Integer.parseInt(st.nextToken());; //도착점 인덱스


        Node[] adjList = new Node[V];

        for(int i=0; i<E; ++i){
            st = new StringTokenizer(in.readLine().trim(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            //인접 리스트 정보 만들기
            adjList[from] = new Node(to, weight, adjList[from]);
        }

        System.out.println(getMinDistance(adjList, start, end));
    }

    private static int getMinDistance(Node[] adjList, int start, int end) {
        final int V = adjList.length;
        int[] minDistance = new int[V];
        minDistance[start] = 0;
        final int INF = Integer.MAX_VALUE;

        Arrays.fill(minDistance, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(adjList[start]);

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.vertex] < cur.weight) continue;

            for (Node e = adjList[cur.vertex]; e!=null; e=e.next) {
                if (minDistance[e.vertex]>cur.weight+e.weight) {
                    minDistance[e.vertex] = cur.weight+e.weight;
                    pq.offer(new Node(e.vertex, minDistance[e.vertex], null));
                }
            }
        }

        return minDistance[end]!=INF ? minDistance[end] : -1;
    }


    static String input = "6 11\r\n"
            + "0 5\r\n"
            + "0 1 3\r\n"
            + "0 2 5\r\n"
            + "1 2 2\r\n"
            + "1 3 6\r\n"
            + "2 1 1\r\n"
            + "2 3 4\r\n"
            + "2 4 6\r\n"
            + "3 4 2\r\n"
            + "3 5 3\r\n"
            + "4 0 3\r\n"
            + "4 5 6";
}
