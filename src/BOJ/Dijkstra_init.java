package BOJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra_init {

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node>[] adjList;
    static int[] minDistance;

    public static void main(String[] args) {
        adjList = new ArrayList[6];
        minDistance = new int[6];

        adjList[0] = new ArrayList<>(Arrays.asList(new Node(1, 10), new Node(2, 15), new Node(3, 40)));
        adjList[1] = new ArrayList<>(Arrays.asList(new Node(3, 20)));
        adjList[2] = new ArrayList<>(Arrays.asList(new Node(3, 11)));
        adjList[3] = new ArrayList<>(Arrays.asList(new Node(4, 2), new Node(5, 5)));
        adjList[4] = new ArrayList<>(Arrays.asList(new Node(5, 2)));
        adjList[5] = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }

        getMinimum(0);

        for(int dis:minDistance) {
            System.out.println(dis);
        }
    }

    private static void getMinimum(int from) {
        minDistance[from] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.price, b.price));
        pq.offer(new Node(from, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (minDistance[cur.n] < cur.price) continue;

            for(Node now: adjList[cur.n]) {
                if (minDistance[now.n] <= minDistance[now.n] + now.price) continue;
                minDistance[now.n] = minDistance[cur.n]+now.price;
                pq.offer(new Node(now.n, minDistance[now.n]));
            }
        }
    }

    static class Node {
        int n, price;

        public Node(int n, int price) {
            this.n = n;
            this.price = price;
        }
    }
}
