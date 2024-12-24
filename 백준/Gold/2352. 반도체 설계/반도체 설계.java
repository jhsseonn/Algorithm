import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] conductor;
    static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        conductor = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            conductor[i] = Integer.parseInt(st.nextToken());
        }

        for (int num : conductor) {
            int pos = binarySearch(num);
            if (pos == lis.size()) {
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
        }

        System.out.println(lis.size());
    }

    private static int binarySearch(int key) {
        int low = 0, high = lis.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (lis.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
