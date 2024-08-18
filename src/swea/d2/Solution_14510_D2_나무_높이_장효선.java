package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510_D2_나무_높이_장효선 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case < T+1; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] trees = new int[N];
            int maxHeight = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, trees[i]);
            }

            int result = getTreeDays(N, trees, maxHeight);
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static int getTreeDays(int N, int[] trees, int maxHeight) {
        int[] heightAbs = new int[N];
        int days = 0;
        int one = 0;
        int two = 0;

        for (int i = 0; i < N; i++) {
            heightAbs[i] = maxHeight-trees[i];
        }

        for (int i = 0; i < N; i++) {
            days+=(heightAbs[i]/3)*2;
            if (heightAbs[i]%3==1) {
                one+=1;
            }
            if (heightAbs[i]%3==2) {
                two+=1;
            }
        }

        while(one>0 && two>0) {
            one -= 1;
            two -= 1;
            days += 2;
        }

        if (one>0) {
            if (one%3==0) {
                days+=(one/3)*2;
            } else if (one%3==1) {
                days+=(one/3)*2;
                if (days%2==0) {
                    days+=1;
                } else {
                    days+=2;
                }
            } else if (one%3==2) {
                days+=(one/3)*2;
                if (days%2==0) {
                    days+=3;
                } else {
                    days+=4;
                }
            }
        }

        if (two>0) {
            two*=2;
            if (two%6==0) {
                days+=(two/6)*4;
            } else if (two%6==4) {
                days+=(two/6)*4;
                days+=3;
            } else if (two%6==2) {
                days+=(two/6)*4;
                if (days%2==0) {
                    days+=2;
                } else {
                    days+=1;
                }
            }
        }

        return days;
    }
}
