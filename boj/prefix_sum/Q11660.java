package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + nextInt(br);
            }
        }

        while(m-- > 0){
            int x1 = nextInt(br), y1 = nextInt(br);
            int x2 = nextInt(br), y2 = nextInt(br);

            sb.append(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while((c = reader.read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while(isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
