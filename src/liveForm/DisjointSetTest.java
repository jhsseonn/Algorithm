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
		
	}
	//find함수 구현해 주세요
	
	public static boolean union(int a, int b) {
		
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
