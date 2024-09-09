package liveForm;

import java.util.Arrays;
import java.util.Scanner;

public class CombinationTest1 {
	static int N;
	static int R;
	static int[] numbers;	//뽑은 r개 수의 조합을 저장할 배열
	static int[] input;		//입력된 N개의 데이타
	public static void main(String[] args) {
		N = 3;
		R = 2;
		input 	= new int[] {1,2,3};
		numbers	= new int [R]; 
				
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		}
		
	}

}
