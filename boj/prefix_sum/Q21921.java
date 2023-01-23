package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int x = nextInt(br) - 1;

        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            dp[i] = dp[i - 1] + nextInt(br);
        }

        int maxVlu = 0, cnt = 0;
        for(int i = 1; i <= n - x; i++){
            int vlu = dp[i + x] - dp[i - 1];

            if(vlu == maxVlu)cnt++;
            else if (vlu > maxVlu) {
                maxVlu = vlu;
                cnt = 1;
            }
        }

        if (maxVlu == 0) {
            System.out.println("SAD");
        }else{
            System.out.printf("%d\n%d\n", maxVlu, cnt);
        }
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
