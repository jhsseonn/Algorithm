package liveForm;

import java.util.Arrays;

// 9P9   : 0.009	    (안전)
// 10P10 : 0.1초 컷		(약간 위험) 
public class Permutation2_nPr4_bit {

    static int R;					//뽑을 개수			
    static int N;					//원소의 개수
    static int[] numbers;			//순열을 담은 배열
    static int[] input;				//입력된 데이타 

    public static void main(String[] args) {
        input = new int[]{1, 2, 3};
        N = input.length;
        R = input.length;
        numbers = new int[R];
    }
    
}
