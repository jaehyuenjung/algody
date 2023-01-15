package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        int[][] dp = new int[26][200000 + 1];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < 26; j++) {
                dp[j][i + 1] = dp[j][i];
            }
            dp[str.charAt(i) - 'a'][i + 1]++;
        }

        int q = nextInt(br);
        while (q-- > 0) {
            char c = nextChar(br);
            int i = nextInt(br), j = nextInt(br);

            sb.append(dp[c - 'a'][j + 1] - dp[c - 'a'][i]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static char nextChar(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) <= 32);
        return (char) c;
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
