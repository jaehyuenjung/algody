package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int[] weights = new int[n], heights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = nextInt(br);
            heights[i] = nextInt(br);
        }

        for (int i = 0; i < n; i++) {
            int sq = 1;
            for (int j = 0; j < n; j++) {
                if (i != j
                        && weights[i] < weights[j]
                        && heights[i] < heights[j]) {
                    sq++;
                }
            }
            sb.append(sq).append(" ");
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
}
