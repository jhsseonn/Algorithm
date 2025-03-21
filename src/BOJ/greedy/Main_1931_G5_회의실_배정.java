package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1931_G5_회의실_배정 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(s, e);
        }

        Arrays.sort(meetings);
        List<Meeting> ans = new ArrayList<>();
        ans.add(meetings[0]);

        for (int i = 1; i < N; i++) {
            if (ans.get(ans.size() - 1).end <= meetings[i].start) {
                ans.add(meetings[i]);
            }
        }

        System.out.print(ans.size());
    }

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end != o.end ? this.end - o.end : this.start - o.start;
        }
    }
}
