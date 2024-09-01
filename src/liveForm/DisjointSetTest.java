package liveForm;

import java.util.Arrays;

/** 
 *  시간 복잡도
 *   2N(make+path압축) + union횟수(E)       1union시 2원소의 find함수 수행
 *   ===>2N(make+path압축) + 4E(2find*2)   path압축됐을 경우
 *   ==> O(4E) 
 */
public class DisjointSetTest {
	static int N;
	static int[] parents;
	public static void make() {
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	//find함수 구현해 주세요
	public static int findSet(int a) {
		if (parents[a]==a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;

//		parents[aRoot]+=parents[bRoot];
		parents[aRoot] = bRoot;
		return true;
	}
	public static void main(String[] args) {
		N= 6;
		make();
		union(1, 2);
		System.out.println(Arrays.toString(parents));
		union(3, 4);
		System.out.println(Arrays.toString(parents));
		union(5, 3);
		System.out.println(Arrays.toString(parents));
		union(1, 5);
		System.out.println(Arrays.toString(parents));
	}
}
