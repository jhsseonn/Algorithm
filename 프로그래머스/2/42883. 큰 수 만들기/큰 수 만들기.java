import java.util.*;
import java.io.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        char[] num = number.toCharArray();
        Stack<Character> ans = new Stack<>();

        for (char n:num) {
            while(!ans.isEmpty() && k > 0 && ans.peek()-'0' < n-'0') {
                ans.pop();
                k--;
            }
            ans.push(n);
        }

        if (k-- > 0) {
            ans.pop();
        }

        for (char n:ans) {
            answer.append(n);
        }

        return answer.toString();
    }
}