package boj.mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int[] cnt = new int[m];

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i - 1] + nextInt(br)) % m;
            cnt[dp[i]]++;
        }

        long answer = cnt[0];
        for (int i = 0; i < m; i++) {
            answer += (long) cnt[i] * (cnt[i] - 1) / 2;
        }

        System.out.println(answer);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while ((c = reader.read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
