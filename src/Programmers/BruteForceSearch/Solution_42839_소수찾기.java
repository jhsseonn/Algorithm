package Programmers.BruteForceSearch;

import java.util.HashSet;

public class Solution_42839_소수찾기 {
    HashSet<Integer> set = new HashSet<>();
    boolean[] visited;

    public int solution(String numbers) {
        int answer = 0;

        visited = new boolean[numbers.length()];

        dfs(numbers, "");

        for (int num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    private void dfs(String numbers, String current) {
        if (!current.equals("")) {
            set.add(Integer.parseInt(current));
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(numbers, current + numbers.charAt(i));
            visited[i] = false;
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
