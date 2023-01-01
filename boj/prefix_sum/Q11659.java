package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);

        int[] dp = new int[n + 2];
        for(int i = 1; i <= n; i++){
            int v = nextInt(br);
            dp[i] = dp[i - 1] + v;
        }

        while(m-- > 0){
            int i = nextInt(br);
            int j = nextInt(br);
            sb.append(dp[j] - dp[i - 1]).append("\n");
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