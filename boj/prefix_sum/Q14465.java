package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int k = nextInt(br) - 1;
        int b = nextInt(br);

        int[] dp = new int[n + 1];
        while (b-- > 0) {
            dp[nextInt(br)]++;
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i];
        }

        int answer = 100_000;
        for (int i = 1; i <= n - k; i++) {
            int sum = dp[i + k] - dp[i - 1];
            answer = Math.min(answer, sum);
        }

        System.out.println(answer);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while ((c = reader.read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
