package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 메모리: 20,552kb / 시간: 123ms
 */
public class Solution_1238_D4_Contact_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Node[] nodes;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            nodes = new Node[101];
            depth = new int[101];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                nodes[from] = new Node(to, nodes[from]);
            }

            sb.append("#").append(test_case).append(" ").append(bfs(S)).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        int max = Integer.MIN_VALUE;
        int result = 0;

        visited[start] = true;
        depth[start] = 1;
        dq.offer(start);

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (Node temp = nodes[cur]; temp != null; temp = temp.next) {
                if (visited[temp.to]) continue;
                visited[temp.to] = true;
                depth[temp.to] = depth[cur] + 1;
                dq.offer(temp.to);
            }
        }

        for (int i = 0; i < depth.length; i++) {
            if (max <= depth[i]) {
                max = depth[i];
                result = Math.max(result, i);
            }
        }

        return result;
    }

    static class Node {
        int to;
        Node next;

        public Node(final int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }
}
