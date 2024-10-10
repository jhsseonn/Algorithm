package ssafy12;

/**
 * N번째 수열을 구할 때 N이 크면 컴퓨터로 표현할 수 없는 수가 되므로 보통 특정 수로 모듈러 연산한 결과를 출력
 * --> 피보나치 수열을 특정 수로 모듈러 연산하면 피사노 주기를 갖게 된다
 * ----> N번째 수열을 모두 구하지 않고 피사노 주기의 크기만큼만 구하면 된다
 */
public class PisanoPeriodTest {
    public static void main(String[] args) {
        int n = 24;
        int[] fibo = new int[n];
        fibo[0] = 0;
        fibo[1] = 1;
        fibo[2] = 2;
        for (int i = 3; i < 24; i++) {
            fibo[i] = fibo[i-1]+fibo[i-2];
        }

        System.out.println("index\t피보나치수열\t피사노주기");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t\t%d%n", i+1, fibo[i], fibo[i]%3);
        }
    }
}
