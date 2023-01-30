package boj.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q1451 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        long[][] dp = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + nextChar(br);
            }
        }

        long answer = 0;

        for (int i = 1; i < n; i++) {
            long a = dp[i][m], ra = dp[n][m] - a;
            for (int j = 1; j < m; j++) {
                long b = dp[n][j] - dp[i][j], rb = dp[i][j];
                long c = dp[n][m] - a - b, rc = dp[n][m] - ra - rb;
                answer = Math.max(answer, a * b * c);
                answer = Math.max(answer, ra * rb * rc);
            }

            for (int j = i + 1; j < n; j++) {
                long b = dp[j][m] - a;
                long c = dp[n][m] - a - b;
                answer = Math.max(answer, a * b * c);
            }
        }

        for (int i = 1; i < m; i++) {
            long a = dp[n][i], ra = dp[n][m] - a;
            for (int j = 1; j < n; j++) {
                long b = dp[j][m] - dp[j][i], rb = dp[j][i];
                long c = dp[n][m] - a - b, rc = dp[n][m] - ra - rb;
                answer = Math.max(answer, a * b * c);
                answer = Math.max(answer, ra * rb * rc);
            }

            for (int j = i + 1; j < m; j++) {
                long b = dp[n][j] - a;
                long c = dp[n][m] - a - b;
                answer = Math.max(answer, a * b * c);
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int nextChar(Reader reader) throws IOException {
        int n;
        while ((n = reader.read()) <= 32);
        return n & 15;
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
        return 47 < c & c < 58;
    }
}
