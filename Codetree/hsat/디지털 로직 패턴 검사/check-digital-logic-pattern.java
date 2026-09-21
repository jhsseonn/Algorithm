import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int K = scanner.nextInt();
        int M = scanner.nextInt();
        int answer = 0;

        HashMap<Long, Integer> map = new HashMap<>();

        long pattern = 0;
        long mask = (1L<<K)-1;

        // 최초 패턴 갱신
        for (int i=0; i<K; i++) {
            pattern = (pattern<<1)|(s.charAt(i)-'0');
        }

        map.put(pattern, 1);

        // 슬라이딩 윈도우
        for (int i = K; i<s.length(); i++) {
            int bit = s.charAt(i)-'0';  // 다음 패턴에 포함될 자리
            pattern = ((pattern<<1)|bit) & mask;

            int count = map.getOrDefault(pattern, 0)+1;
            map.put(pattern, count);

            if (count>=M) {
                answer = 1;
                break;
            }
        }

        System.out.println(answer);
    }
}