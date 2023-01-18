package boj.sieve_of_eratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q17425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[1_000_000 + 1];
        for (int i = 1; i <= 1_000_000; i++) {
            for (int j = 1; i * j <= 1_000_000; j++) {
                dp[i * j] += i;
            }
            dp[i] += dp[i - 1];
        }

        int t = nextInt(br);

        while (t-- > 0) {
            sb.append(dp[nextInt(br)]).append("\n");
        }

        System.out.print(sb);
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
