package BOJ.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 133,708kb / 1,840ms
 */
public class Main_1202_G2_보석도둑 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] bags = new int[K];
		long result = 0;
		Jewel[] jewels = new Jewel[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			bags[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(bags);
		Arrays.sort(jewels);
		
		// 가방 돌면서 최고 제한 무게보다 작거나 같은 경우 가격이 가장 비싼 경우를 합한다
		int idx = 0;
		for (int i = 0; i < K; i++) {
			while(idx<N && bags[i]>=jewels[idx].m) {
				pq.offer(jewels[idx].v);
				idx++;
			}
			if (!pq.isEmpty()) {
				result += pq.poll();
			}
		}
		
		System.out.println(result);
	}
	
	static class Jewel implements Comparable<Jewel>{
		int m;
		int v;
		
		public Jewel(int m, int v) {
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jewel o) {
			return this.m!=o.m ? Integer.compare(this.m, o.m) : Integer.compare(o.v, this.v);
		}
	}

}
