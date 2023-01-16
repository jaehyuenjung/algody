package boj.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);

        int[] arr = new int[n + 1];
        int[][] dp = new int[4][n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + nextInt(br);
        }

        int m = nextInt(br);

        for (int i = 1; i <= 3; i++) {
            for (int j = i * m; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + arr[j] - arr[j - m]);
            }
        }

        System.out.println(dp[3][n]);
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
