package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q10211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = nextInt(br);

        int[] arr = new int[1000];
        while (t-- > 0) {
            int n = nextInt(br);

            for (int i = 0; i < n; i++) {
                arr[i] = nextInt(br);
            }

            int answer = -1000;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += arr[j];
                    answer = Math.max(answer, sum);
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        boolean isNeg = false;
        while ((c = reader.read()) <= 32 || isSign(c)) isNeg = c == 45;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = reader.read()));
        return isNeg ? -n : n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }

    static boolean isSign(int c) {
        return c == 43 || c == 45;
    }
}
