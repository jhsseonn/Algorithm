import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			int K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> tm = new TreeMap<>();

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				char cal = st.nextToken().charAt(0);
				int N = Integer.parseInt(st.nextToken());

				if (cal=='I') {
					tm.put(N, tm.getOrDefault(N, 0)+1);
				} else if (cal=='D') {
					if (tm.isEmpty()) continue;
					if (N == -1) {
						int first = tm.firstKey();
						int value = tm.getOrDefault(first, 0);
						if (value==1 || value==0) tm.remove(first);
						else tm.put(first, value-1);
					} else if (N == 1) {
						int last = tm.lastKey();
						int value = tm.getOrDefault(last, 0);
						if (value==1 || value==0) tm.remove(last);
						else tm.put(last, value-1);
					}
				}
			}
			if (tm.isEmpty()) {
				sb.append("EMPTY").append("\n");
				continue;
			}

			sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
		}

		System.out.println(sb);
	}
}
