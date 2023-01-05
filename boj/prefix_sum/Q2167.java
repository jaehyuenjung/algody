package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2167 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + nextInt(br);
            }
        }

        int k = nextInt(br);
        while(k-- > 0){
            int i = nextInt(br), j = nextInt(br);
            int x = nextInt(br), y = nextInt(br);
            sb.append(dp[x][y] - dp[x][j - 1] - dp[i - 1][y] + dp[i - 1][j - 1]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        boolean isNeg = false;
        while ((c = reader.read()) <= 32 || isSign(c)) isNeg = c == 45;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber((c = reader.read())));
        return isNeg ? -n : n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }

    static boolean isSign(int c){
        return c == 43 || c == 45;
    }
}
