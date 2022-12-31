package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import utils.java.InputStreamReader;

public class Q11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] dp = new int[n + 2];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++){
            int v = Integer.parseInt(st.nextToken());
            dp[i] = dp[i - 1] + v;
        }

        while(m-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            System.out.println(dp[j] - dp[i - 1]);
        }
    }
}