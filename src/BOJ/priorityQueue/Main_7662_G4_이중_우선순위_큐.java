package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_7662_G4_이중_우선순위_큐 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> qMin;
	static PriorityQueue<Integer> qMax;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		qMin = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		qMax = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});

		for (int i = 0; i < T; i++) {
			int K = Integer.parseInt(br.readLine());
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				char cal = st.nextToken().charAt(0);
				int N = Integer.parseInt(st.nextToken());

				if (cal=='I') {
					qMin.offer(N);
					qMax.offer(N);
				} else if (cal=='D') {
					if (!qMin.isEmpty() && !qMax.isEmpty()) {
						if (N == -1) {
							int r = qMin.poll();
							qMax.remove(r);
						} else if (N == 1) {
							int r = qMax.poll();
							qMin.remove(r);
						}
					}
				}
			}
			if (qMin.isEmpty() && qMax.isEmpty()) {
				sb.append("EMPTY").append("\n");
				continue;
			}
			sb.append(qMax.poll()+" "+qMin.poll()).append("\n");
		}
		
		System.out.println(sb);
	}

}
