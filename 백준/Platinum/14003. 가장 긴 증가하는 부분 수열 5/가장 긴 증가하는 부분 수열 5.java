import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> lis = findLIS();

        System.out.println(lis.size());  // lis 크기 출력
        while(!lis.isEmpty()) {
            System.out.print(lis.pop()+" ");
        }
    }

    private static Stack<Integer> findLIS() {
        int[] lis = new int[N];
        int[] pos = new int[N];
        int[] prev = new int[N];
        Stack<Integer> sequence = new Stack<>();

        int length = 0;

        for (int i = 0; i < N; i++) {
            int idx = binarySearch(lis, arr[i], length);
            lis[idx] = arr[i];
            pos[idx] = i;
            prev[i] = (idx > 0) ? pos[idx-1] : -1;

            if (idx == length) {
                length++;
            }
        }

        int currentIdx = pos[length-1];
        while (currentIdx != -1) {
            sequence.push(arr[currentIdx]);
            currentIdx = prev[currentIdx];
        }

        return sequence;
    }

    private static int binarySearch(int[] lis, int key, int length) {
        int from = 0, end = length - 1;

        while (from <= end) {
            int mid = from + (end - from) / 2;
            if (lis[mid] < key) {
                from = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return from;
    }
}
