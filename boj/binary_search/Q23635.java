package boj.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q23635 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = nextInt(br);
        int n = nextInt(br);

        int[] dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + nextInt(br);
        }

        int mxVlu, answer;
        mxVlu = answer = dp[n];
        for (int vlu = 1; vlu <= mxVlu; vlu++) {
            int i, start = 1, result = 0;
            for (i = 0; i < k && start <= n; i++) {
                int left = start, right = n;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (vlu <= dp[mid] - dp[start - 1]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left <= n) {
                    result += dp[left] - dp[start - 1];
                    start = left + 1;
                } else {
                    break;
                }
            }

            if (i < k) {
                break;
            } else {
                answer = Math.min(answer, result - k * vlu);
            }
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
