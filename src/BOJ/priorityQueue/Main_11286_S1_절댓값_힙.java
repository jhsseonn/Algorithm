package BOJ.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11286_S1_절댓값_힙 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static PriorityQueue<Integer> queue;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                if (Math.abs(o1) > Math.abs(o2)){
                    return Math.abs(o1)-Math.abs(o2);
                } else if (Math.abs(o1)==Math.abs(o2)) {
                    return o1-o2;
                } else return -1;
            }
        });

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input!=0) {
                queue.offer(input);
            } else {
                if (queue.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(queue.poll()).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
