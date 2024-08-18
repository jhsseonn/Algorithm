package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_1225_D3_암호_생성기_장효선 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            int testCase = Integer.parseInt(st.nextToken());
            sb.append("#").append(testCase);

            st = new StringTokenizer(br.readLine());
            Deque<Integer> numbers = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }

            int number = 1;
            while (number>0) {
                for (int i = 1; i <= 5; i++) {
                    int num = numbers.pop()-i;
                    if (num<=0) {
                        num = 0;
                        numbers.add(num);
                        number = 0;
                        break;
                    }
                    numbers.add(num);
                }
            }

            for (int num:numbers) {
                sb.append(" ").append(num);
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
