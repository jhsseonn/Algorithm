package swea.모의SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2383_모의_점심식사_시간_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, S, minTime;
	static int[][] room;
	static List<Person> people;
	static List<Stair> stairs;
	static List<Person> peopleSubset;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			peopleSubset = new ArrayList<>();
			minTime = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					room[i][j] = n;
					if (n==1) {
						people.add(new Person(i, j));
					}
					if (n!=0 && n!=1) {
						stairs.add(new Stair(i, j, n));
					}
				}
			}
			
			getSubset(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void getSubset(int depth, int len) {
		if (depth==people.size()) {
			minTime = Math.min(minTime, evaluate(peopleSubset.subList(0, len)));
			return;
		}
		peopleSubset.add(people.get(len));
		getSubset(depth+1, len+1);
		getSubset(depth+1, len);
	}
	
	private static int evaluate(List<Person> subset1) {
		List<Person> subset2 = getOtherSubset(subset1);
		Stair s1 = stairs.get(0);
		Stair s2 = stairs.get(1);
		Person[] p1 = new Person[subset1.size()];
		Person[] p2 = new Person[subset2.size()];
		
		// 계단까지 걸리는 시간 할당(기다리는 시간 포함)
		for (int i = 0; i < subset1.size(); i++) {
			Person p = subset1.get(i);
//			System.out.println(p.x+" "+p.y+" "+p.minute);
			p.minute = Math.abs(p.x-s1.x)+Math.abs(p.y-s1.y)+1;
			p1[i] = p;
		}
		
		for (int i = 0; i < subset2.size(); i++) {
			Person p = subset2.get(i);
//			System.out.println(p.x+" "+p.y+" "+p.minute);
			p.minute = Math.abs(p.x-s2.x)+Math.abs(p.y-s2.y)+1;
			p2[i] = p;
		}
		
		Arrays.sort(p1);
		Arrays.sort(p2);
				
		Deque<Person> dq1 = new ArrayDeque<>();
		Deque<Person> dq2 = new ArrayDeque<>();
		
		// 각각 계단을 내려오는 데 걸리는 시간 계산하기
		// 계단 1번에서 사람들이 내려오는 시간 계산
		if (subset1.size()!=0) {
			dq1.offer(subset1.get(0));
		}
		
		int stair1Minute = 0;
		int idx = 1;
		while(!dq1.isEmpty()) {
			if (idx==subset1.size()) {
				Person p = dq1.poll();
				stair1Minute = p.minute;
				p.minute=0;
				continue;
			}
			
			// 큐의 크기가 3보다 작으면 큐에 사람 넣어주기
			if (dq1.size()<3) {
				dq1.offer(subset1.get(idx));
				idx+=1;
			} else {
				Person p = dq1.poll();
				Person cur = subset1.get(idx);
				if (cur.minute - p.minute>=s1.len) {
					dq1.offer(cur);
					idx+=1;
				} else {
					cur.minute+=s1.len-(cur.minute - p.minute);
					dq1.offer(cur);
					idx+=1;
				}
				p.minute=0;
			}
		}
		
		// 계단 2번에서 사람들이 내려오는 시간 계산하기
		if (subset2.size()!=0) {
			dq2.offer(subset2.get(0));
		}
		int stair2Minute = 0;
		idx = 1;
		while(!dq2.isEmpty()) {
			if (idx==subset2.size()) {
				Person p = dq2.poll();
				stair2Minute = p.minute;
				p.minute=0;
				continue;
			}
			
			// 큐의 크기가 3보다 작으면 큐에 사람 넣어주기
			if (dq2.size()<3) {
				dq2.offer(subset2.get(idx));
				idx+=1;
			} else {
				Person p = dq2.poll();
				Person cur = subset2.get(idx);
				if (cur.minute - p.minute>=s2.len) {
					dq2.offer(cur);
					idx+=1;
				} else {
					cur.minute+=s2.len-(cur.minute - p.minute);
					dq2.offer(cur);
					idx+=1;
				}
				p.minute=0;
			}
		}
		
		return Math.max(stair1Minute, stair2Minute);
	}
	
	private static List<Person> getOtherSubset(List<Person> subset1) {
		List<Person> p = new ArrayList<>();
		for (Person person : people) {
			if (!subset1.contains(person)) {
				p.add(person);
			}
		}
		return p;
	}
	
	
	static class Person implements Comparable<Person>{
		int x, y, minute;

		public Person(int x, int y) {
			this.x = x;
			this.y = y;
			this.minute = 0;
		}

		@Override
		public int compareTo(Person o) {
			return this.minute - o.minute;
		}
	}
	
	static class Stair {
		int x, y, len;

		public Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}

}
