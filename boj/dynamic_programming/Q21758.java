package boj.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q21758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);

        int[] arr = new int[n + 1], dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = nextInt(br);
            dp[i] = dp[i - 1] + arr[i];
        }

        int answer = 0;
        for (int i = 2; i < n; i++) {
            answer = Math.max(answer, dp[n] - arr[1] - arr[i] + dp[n] - dp[i]);
            answer = Math.max(answer, dp[n] - arr[n] - arr[i] + dp[i - 1]);
            answer = Math.max(answer, dp[i] - arr[1] + dp[n] - dp[i - 1] - arr[n]);
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
