package BOJ.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_31964_반품_회수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer X = new StringTokenizer(br.readLine());
        StringTokenizer T = new StringTokenizer(br.readLine());
        Pos[] arr = new Pos[N];

        for (int i = 0; i < N; i++) {
            int dist = Integer.parseInt(X.nextToken());
            int time = Integer.parseInt(T.nextToken());
            arr[i] = new Pos(dist, time);
        }

        int time;
        if (arr[N-1].dist>=arr[N-1].time) {
            time = arr[N - 1].dist;
        } else {
            time = arr[N-1].time;
        }
        int dist = arr[N - 1].dist;
        int idx = N - 2;
        while (dist > 0) {
            time++;
            dist--;
            if (idx >= 0 && arr[idx].dist == dist) {
                if (arr[idx].time > time) {
                    time = arr[idx].time;
                }
                idx--;
            }
        }

        System.out.println(time);
    }

    static class Pos {
        int dist, time;

        public Pos(final int dist, final int time) {
            this.dist = dist;
            this.time = time;
        }
    }
}
