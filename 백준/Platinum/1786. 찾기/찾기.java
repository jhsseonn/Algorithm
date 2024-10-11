import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String o = br.readLine();
        String p = br.readLine();
        List<Integer> res = kmp(o, p);

        System.out.println(res.size());
        for (int ans : res) {
            System.out.print(ans + " ");
        }
    }

    static int[] getPI(String pattern) {
        int n = pattern.length();
        int[] pi = new int[n];
        int j = 0;

        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    static List<Integer> kmp(String o, String p) {
        int[] pi = getPI(p);
        int j = 0;
        int plen = p.length();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0, end = o.length(); i < end; i++) {
            while (j > 0 && o.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }
            if (o.charAt(i) == p.charAt(j)) {
                if (j == plen - 1) {
//                    System.out.println((i - plen + 1) + "째 인덱스에서 발견");
                    ans.add(i - plen + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return ans;
    }
}
