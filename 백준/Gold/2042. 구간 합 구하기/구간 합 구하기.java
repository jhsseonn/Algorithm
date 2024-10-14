import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        nums = new long[N];
        tree = new long[4*N];

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        init(0, N-1, 1);

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a==1) {
                long c = Long.parseLong(st.nextToken());
                update(0, N-1, 1, b-1, c);
            } else if (a==2) {
                int r = Integer.parseInt(st.nextToken());
                sb.append(sum(0, N-1, 1, b-1, r-1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static long init(int start, int end, int node) {
        if (start==end) return tree[node] = nums[start];
        int mid = (start+end)/2;
        return tree[node] = init(start, mid, node*2)+init(mid+1, end, node*2+1);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (left>end || right<start) return 0;
        if (left<=start && right>=end) return tree[node];
        int mid = (start+end)/2;
        return sum(start, mid, node*2, left, right)+sum(mid+1, end, node*2+1, left, right);
    }

    private static void update(int start, int end, int node, int index, long dif) {
        if (index>end || index < start) return;
        if (start==end) {
            nums[index] = dif;
            tree[node] = dif;
            return;
        }
        int mid = (start+end)/2;
        update(start, mid, node*2, index, dif);
        update(mid+1, end, node*2+1, index, dif);
        tree[node] = tree[node*2]+tree[node*2+1];
    }
}
