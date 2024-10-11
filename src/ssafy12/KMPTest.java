package ssafy12;

/**
 * KMP(Knuth, Morris, Prett)
 * - 접두사와 접미사 개념을 활용해 '반복되는 연산을 얼마나 줄일 수 있는지'를 판볋하여
 * 매칭할 문자열을 빠르게 점프하는 기법
 * - 접두사와 접미사가 일치하는 길이(Fail Table - 일치하는 길이만큼 점프)를 구해야한다.
 * <p>
 * 1. 접미사 테이블(Fail Table)을 생성한다
 * - 접두사 j = 0 / 접미사 i = 1 부터 문자 비교
 * - i와 j 위치의 문자가 일치하면 i, j 위치는 모두 증가하고, fail table에는 증가한 j값(일치한 문자 개수)을 저장한다.
 * - i와 j 위치의 문자가 일치하지 않으면 i는 고정, j 위치를 이동 j=fail[j-1]
 * ==> 문자가 일치하지 않거나 j>0일 때 계속 j 위치를 이동해서 비교한다.
 * ==> j번째 문자가 일치하지 않으므로 일치했던 위치 j-1에 일치한 개수가 기록되어 있으므로 해당 위치로 이동해서 또 비교한다.
 * - j가 0이면 일치하는 패턴이 더이상 없으므로 i는 증가 후 j=0와 다시 비교
 * <p>
 * 2. 원문과 패턴을 비교해서 불일치 시 Fail Table을 이용해서 다음을 비교한다.
 * - i=0(본문을 비교할 변수), j=0(패턴 비교를 위한 변수)
 * - i와 j 위치의 문자가 일치하면 i, j 위치를 모두 증가시킨다. 단) j가 패턴의 끝이면, 패턴과 일치하는 본문을 발견
 * - i와 j 위치의 문자가 일치하지 않으면, i는 고정 j는 j=fail[j-1]로 이동, 같은 문자가 발견될 때까지 j를 이동하거나 j==0이면 종료
 * - j가 0이면 일치하는 패턴이 더이상 없으므로 j를 증가시킨 위치와 j=0 위치의 문자를 다시 비교
 */
public class KMPTest {
    public static void main(String[] args) {
        String o = "ababacabacaabacaaba";
        String p = "abacaaba";
        kmp(o,p);
    }

    static int[] getPI(String pattern) {
        int n = pattern.length();

        // 접두와 접미사가 일치하는 최대 길이를 담을 배열
        int[] pi = new int[n];

        int j = 0;  // 접두사 index

        /*
        접두사 j = 0 / 접미사 i = 1 부터 문자 비교
     *    - i와 j 위치의 문자가 일치하면 i, j 위치는 모두 증가하고, fail table에는 증가한 j값(일치한 문자 개수)을 저장한다.
     *    - i와 j 위치의 문자가 일치하지 않으면 i는 고정, j 위치를 이동 j=fail[j-1]
     *      ==> 문자가 일치하지 않거나 j>0일 때 계속 j 위치를 이동해서 비교한다.
     *      ==> j번째 문자가 일치하지 않으므로 일치했던 위치 j-1에 일치한 개수가 기록되어 있으므로 해당 위치로 이동해서 또 비교한다.
     *    - j가 0이면 일치하는 패턴이 더이상 없으므로 i는 증가 후 j=0와 다시 비교
         */

        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            /*
                while문이 끝난 경우는 j가 0이거나, 두 문자가 일치한 경우
                j가 0인 경우        : i를 증가시키고 j=0부터 다시 비교
                두 문자가 일치한 경우 : i, j 증가시키고, PI[i] = 증가 시킨 j값 저장

             */
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    static void kmp(String o, String p) {
        int[] pi = getPI(p);
        // pattern을 위한 index
        int j = 0;

        /**
         * i=0(본문을 비교할 변수), j=0(패턴 비교를 위한 변수)
     *  * - i와 j 위치의 문자가 일치하면 i, j 위치를 모두 증가시킨다. 단) j가 패턴의 끝이면, 패턴과 일치하는 본문을 발견
     *  * - i와 j 위치의 문자가 일치하지 않으면, i는 고정 j는 j=fail[j-1]로 이동, 같은 문자가 발견될 때까지 j를 이동하거나 j==0이면 종료
     *  * - j가 0이면 일치하는 패턴이 더이상 없으므로 j를 증가시킨 위치와 j=0 위치의 문자를 다시 비교
         */
        // i는 원문을 위한 index
        int plen = p.length();
        for (int i = 0, end=o.length(); i<end; i++) {
            while(j>0 && o.charAt(i)!=p.charAt(j)) {
                j=pi[j-1];
            }

            if (o.charAt(i)==p.charAt(j)) {
                if (j==plen-1) {  // 원문에서 패턴과 일치하는 문자열을 찾은 경우
                    System.out.println((i-plen+1)+"째 인덱스에서 발견");
                    // 다음 패턴을 찾기 위해서 i는 증가, j는 또 일치하는 부분을 점프하기 위해서
                    j=pi[j];
                } else {
                    // 원문과 j번째 문자가 일치하지만 패턴이 모두 매치되는게 아니므로
                    // 다음 문자를 비교하기 위해 i, j 증가
                    j++;
                }
            }
        }
    }
}
