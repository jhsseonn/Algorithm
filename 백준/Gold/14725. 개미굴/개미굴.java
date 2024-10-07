import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Node root = new Node();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            String[] arr = new String[M];
            for (int j = 0; j < M; j++) {
                arr[j] = st.nextToken();
            }
            insert(arr);
        }

        getAntHole(root, sb, 0);

        System.out.println(sb);
    }

    public static void insert(String[] arr) {
        Node node = root;

        for (int i = 0; i < arr.length; i++) {
            node.child.putIfAbsent(arr[i], new Node());
            node = node.child.get(arr[i]);
        }

        node.end = true;
    }

    public static void getAntHole(Node node, StringBuilder sb, int depth) {
        for (Map.Entry<String, Node> curNode:node.child.entrySet()) {
            for (int i = 1; i<=depth; i++) {
                sb.append("--");
            }
            sb.append(curNode.getKey()).append("\n");
            getAntHole(curNode.getValue(), sb, depth+1);
        }
    }

    static class Node {
        TreeMap<String, Node> child;
        boolean end;

        public Node() {
            this.child = new TreeMap<>();
            this.end = false;
        }
    }
}
