package liveForm;

import java.util.Arrays;
// 중복 원소를 배열을 이용해서 체크하기 
// 9P9   : 0.01	    	(안전)
// 10P10 : 0.1초 컷		(약간 위험) 

public class Permutation2_nPr3_flag {

    static int R;					//뽑을 개수			
    static int N;					//원소의 개수
    static int[] numbers;			//순열을 담은 배열
    static int[] input;				//입력된 데이타 
    static boolean[] isSelected;	//중복을 체크하기 위한  flag 배열   true는 해당 순열이 사용,  false는 사용 않함

    public static void main(String[] args) {
        input = new int[]{1, 2, 3};
        N = input.length;
        R = input.length;
        numbers = new int[R];
        isSelected = new boolean[N];
    }
     
}
