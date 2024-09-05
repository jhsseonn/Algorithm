package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2477_모의_차량_정비소_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, A, B, cnt, customerCnt, ans;
	static int[] aTime, bTime;
	static PriorityQueue<Customer> aState, bState;
	static boolean[] aIsValid, bIsValid;
	static Customer[] customers;
	
	// 대기 큐
	static PriorityQueue<Customer> waitA = new PriorityQueue<>((a, b) -> {
		return a.time==b.time? a.id - b.id: a.time-b.time;
	});
	static PriorityQueue<Customer> waitB = new PriorityQueue<>((a, b) -> {
		return a.time==b.time? a.a - b.a: a.time-b.time;
	});
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case < T+1; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			aTime = new int[N+1];
			aState = new PriorityQueue<>((a, b) -> (a.time - b.time));
			bTime = new int[M+1];
			bState = new PriorityQueue<>((a, b) -> (a.time - b.time));
			customers = new Customer[K+1];
			aIsValid = new boolean[N+1];
			bIsValid = new boolean[M+1];
			
			Arrays.fill(aIsValid, true);
			Arrays.fill(bIsValid, true);
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N+1; i++) {
				aTime[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M+1; i++) {
				bTime[i] = Integer.parseInt(st.nextToken());				
			}
			
			// a, b의 값이 0인 경우 아직 방문x
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				customers[i] = new Customer(i, Integer.parseInt(st.nextToken()), 0, 0);
			}
			
			// 가장 처음 방문하는 초기값 설정
			aState.offer(customers[1]);
			customers[1].a = 1;
			customers[1].time+=aTime[1];
			aIsValid[1] = false;
			startService();
			
			if (ans==0) ans=-1;
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void startService() {
		// 시간 지날 때마다 
		while(true) {
			// 정비가 끝났으면 뽑고 찾는 사람이 맞는지 확인, 맞으면 id 더해주기
			while(bState.peek().time==cnt) {
				Customer bCur = bState.poll();
				if (bCur.a==A && bCur.b==B) ans+=bCur.id;
				customerCnt++;
//				System.out.println(bCur.id+" "+bCur.a+" "+bCur.b+" "+bCur.time);
				bIsValid[bCur.b] = true;
			}
			
			if (customerCnt==K) break;
			
			// 접수가 끝남
			while(aState.peek().time==cnt) {
				Customer aCur = aState.poll();
				if (bState.size()==bTime.length) { // 정비소 큐가 꽉 찼으면 대기큐로 감
					waitB.offer(aCur);
				}
				else {
					goToB(aCur);
				}
			}
			
			for (Customer cur : customers) {
				if (cur==null) continue;
				if (cur.a==0 && cur.b==0) {
					if (aState.size()==aTime.length) { // 정비소 큐가 꽉 찼으면 대기큐로 감
						waitA.offer(cur);
					}
					else {
						goToA(cur);
					}
				}
			}
			cnt++;
		}
	}
	
	private static void goToB(Customer cur) {
		// 대기큐가 비어있을 경우
		if (waitB.isEmpty()) {
			for (int i = 1; i < bIsValid.length; i++) {
				if (bIsValid[i]) {
					cur.b = i;
					cur.time = cnt+bTime[i];
					bIsValid[i] = false;
					break;
				}
			}
			bState.offer(cur);
		} else { // 대기큐가 비어있지 않은 경우
			Customer waitBCur = waitB.poll();
			for (int i = 1; i < bIsValid.length; i++) {
				if (bIsValid[i]) {
					waitBCur.b = i;
					waitBCur.time = cnt+bTime[i];
					bIsValid[i] = false;
					break;
				}
			}
			bState.offer(waitBCur);
		}
	}
	
	private static void goToA(Customer cur) {
		// 대기큐가 비어있을 경우
		if (waitA.isEmpty()) {
			for (int i = 1; i < aIsValid.length; i++) {
				if (aIsValid[i]) {
					cur.a = i;
					cur.time = cnt+aTime[i];
					aIsValid[i] = false;
					break;
				}
			}
			aState.offer(cur);
		} else { // 대기큐가 비어있지 않은 경우
			Customer waitACur = waitA.poll();
			for (int i = 1; i < aIsValid.length; i++) {
				if (aIsValid[i]) {
					waitACur.b = i;
					waitACur.time = cnt+aTime[i];
					aIsValid[i] = false;
					break;
				}
			}
			aState.offer(waitACur);
		}
	}
	
	private static class Customer {
		int id, time, a, b;

		public Customer(int id, int time, int a, int b) {
			this.id = id;
			this.time = time;
			this.a = a;
			this.b = b;
		}
	}

}
