import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        int minLength = Integer.MAX_VALUE;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i=0; i<phone_book.length; i++) {
            minLength = Math.min(minLength, phone_book[i].length());
            map.put(phone_book[i], 1);
        }
        
        for (int i=0; i<phone_book.length; i++) {
            String cur = phone_book[i];
            for (int j=minLength-1; j<cur.length(); j++) {
                String head = cur.substring(0, j);
                if (map.getOrDefault(head, 0)!=0) return false; 
            }
        }
        
        return answer;
    }
}