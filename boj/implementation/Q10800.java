package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import utils.java.InputStreamReader;

public class Q10800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);

        Ball[] balls = new Ball[n + 1];
        balls[0] = new Ball(0, 0, 0);
        for (int i = 1; i <= n; i++) {
            balls[i] = new Ball(i, nextInt(br), nextInt(br));
        }

        Arrays.sort(balls);

        int sum = 0;
        int[] dp = new int[n + 1];
        int[] dpp = new int[200_000 + 1];
        int[] answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int preId = balls[i - 1].id, curId = balls[i].id;
            int preColor = balls[i - 1].color, curColor = balls[i].color;
            int preSize = balls[i - 1].size, curSize = balls[i].size;

            sum += curSize;
            dp[curColor] += curSize;
            dpp[curSize] += curSize;

            answer[curId] = sum - dp[curColor] - dpp[curSize] + curSize;
            if(curSize == preSize && curColor == preColor){
                answer[curId] = answer[preId];
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

    static class Ball implements Comparable<Ball> {
        int id;
        int color;
        int size;

        public Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball other) {
            if (size == other.size) return color - other.color;
            return size - other.size;
        }
    }
}
