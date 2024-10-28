import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Node>[] adjList;
    static int N, maxLength;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 트리에 존재하는 모든 경로들 중 가장 길이가 긴 것
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        maxLength = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(V, W));
            adjList[V].add(new Node(start, W));
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            visited[i] = true;
            getLength(0, i);
            visited[i] = false;
        }

        System.out.println(maxLength);
    }

    private static void getLength(int len, int start) {
        maxLength = Math.max(maxLength, len);
        for (Node next : adjList[start]) {
            if (visited[next.V]) continue;  // 이미 방문한 노드의 경우 가지 않는다
            visited[next.V] = true;
            getLength(len + next.W, next.V);
            visited[next.V] = false;
        }
    }

    private static class Node {
        int V, W;

        public Node(int v, int w) {
            V = v;
            W = w;
        }
    }
}
