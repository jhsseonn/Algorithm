package BOJ.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1218_D4_괄호_짝짓기_장효선 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int stackLength = Integer.parseInt(st.nextToken());
			int isValid = getValidate(br.readLine());
			
			sb.append("#").append(test_case).append(" ").append(isValid).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int getValidate(String str) {
		Stack<Character> stack = new Stack<>();
		for (char c:str.toCharArray()) {
			if (stack.empty()) {
				stack.push(c);
				continue;
			}
			if (stack.peek()=='(' && c==')') {
				stack.pop();
			} else if (stack.peek()=='[' && c==']') {
				stack.pop();
			} else if (stack.peek()=='{' && c=='}') {
				stack.pop();
			} else if (stack.peek()=='<' && c=='>') {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		if (!stack.empty()) return 0;
		return 1;
	}

}
