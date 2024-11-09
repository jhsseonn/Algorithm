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
    static int N, M, K;
    static List<Integer>[] adjList;
    static int[] candies;
    static boolean[] visited;
    static int[] dp;
    static List<Group> groups;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        candies = new int[N+1];
        visited = new boolean[N+1];
        groups = new ArrayList<>();
        dp = new int[K];

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            Group group = new Group(0, 0);
            getCandies(i, group);
            groups.add(group);
        }

        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(Group g:groups) {
            int childs = g.childs;
            int candies = g.candies;

            for (int i = K-1; i >= childs; i--) {
                if (dp[i-childs]==-1) continue;
                dp[i] = Math.max(dp[i], dp[i-childs]+candies);
            }
        }

        int answer = 0;
        for (int i = 0; i < K; i++) {
            if (dp[i]==-1) continue;
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    private static void getCandies(int num, Group g) {
        visited[num] = true;
        g.candies += candies[num];
        g.childs++;

        for(int child:adjList[num]) {
            if (visited[child]) continue;
            getCandies(child, g);
        }
    }

    private static class Group {
        int candies, childs;

        public Group(int candies, int childs) {
            this.candies = candies;
            this.childs = childs;
        }
    }
}
