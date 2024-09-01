package liveForm;

import java.util.Arrays;
import java.util.Scanner;

public class P4_NextPermutationTest {

	public static void main(String[] args) {
		int[] input = new int[] {1,2,3};
		int N = 3;
		Arrays.sort(input);
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
		
	}
	private static boolean np(int p[]) {
		
		return true;
	}
	
}
