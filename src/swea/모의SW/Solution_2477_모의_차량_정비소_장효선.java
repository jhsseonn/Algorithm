package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 28,124kb / 185ms
 *
 */
public class Solution_2477_모의_차량_정비소_장효선 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, A, B, ans;
	static int[] aTime, bTime;
	static int[] aState, bState;
	static Customer[] customers;
	
	// 대기 큐
	static Deque<Customer> aWait = new ArrayDeque<>();
	static Deque<Customer> bWait = new ArrayDeque<>();
	
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
			aState = new int[N+1];
			bTime = new int[M+1];
			bState = new int[M+1];
			customers = new Customer[K+1];
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			aTime[0] = 0;
			for (int i = 1; i < N+1; i++) {
				aTime[i] = Integer.parseInt(st.nextToken());
			}
			
			bTime[0] = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M+1; i++) {
				bTime[i] = Integer.parseInt(st.nextToken());				
			}
			
			// a, b의 값이 0인 경우 아직 방문x
			customers[0] = null;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				customers[i] = new Customer(i, Integer.parseInt(st.nextToken()), 0, 0);
			}

			startService();
			
			if (ans==0) ans=-1;
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void startService() {
		int arrivedIdx = 1;
		int time = 0;
		int customerCnt = 0;
		while(customerCnt < K) {
			for (int i = arrivedIdx; i < K+1; i++) {
				if (customers[i].time==time) {
					aWait.offer(customers[i]); 
					arrivedIdx+=1;
				}
			}
			// 정비가 끝났으면 뽑고 찾는 사람이 맞는지 확인, 맞으면 id 더해주기
			for (int i = 1; i <= M; i++) {
				if (bState[i]==0) continue;
				Customer cur = customers[bState[i]];
				if (cur.time == time) {
					customerCnt++;					
					bState[i] = 0;
//					System.out.println(cur.a+" "+cur.b+" "+cur.id);
					if (cur.a==A && cur.b==B) ans+=cur.id;
				}
			}
			
			// 정비 대기 큐에 있는 사람을 정비 창구로
			for (int i = 1; i <= M; i++) {
				if (bState[i]==0 && !bWait.isEmpty()) {
					Customer cur = bWait.poll();
					bState[i] = cur.id;
					cur.time = time+bTime[i];
					cur.b = i;
				}
			}
			
			// 접수 창구
			for (int i = 1; i <= N; i++) {
				if (aState[i]==0) continue;
				Customer cur = customers[aState[i]];
				if (cur.time == time) {	
					bWait.offer(cur);
					aState[i] = 0;
				}
			}
			
			// 접수 대기 큐
			for (int i = 1; i <= N; i++) {
				if (aState[i]!=0) continue;
				if (!aWait.isEmpty()) {
					Customer cur = aWait.poll();
					aState[i] = cur.id;
					cur.time = time+aTime[i];
					cur.a = i;						
				}
			}
			
			time++;
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
