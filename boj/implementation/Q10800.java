package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import utils.java.InputStreamReader;

public class Q10800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);

        ArrayList<ArrayList<Ball>> sortedBalls = new ArrayList<>(2000 + 1);
        for(int i = 0; i <= 2000; i++){
            sortedBalls.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            int c = nextInt(br), s = nextInt(br);
            sortedBalls.get(s).add(new Ball(i, c));
        }

        int sum = 0;
        int[] dp = new int[n + 1];
        int[] answer = new int[n + 1];
        for (int i = 1; i <= 2000; i++) {
            for(Ball b : sortedBalls.get(i)){
                answer[b.id] = sum - dp[b.color];
            }
            sum += sortedBalls.get(i).size() * i;
            for(Ball b : sortedBalls.get(i)){
                dp[b.color] += i;
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
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

    static class Ball {
        int id;
        int color;

        public Ball(int id, int color) {
            this.id = id;
            this.color = color;
        }
    }
}
