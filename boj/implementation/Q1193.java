package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);

        int s, i, j;
        for (s = 1, i = 1, j = 2; !(s - i < n && n <= s + j); s += j * 2 + 1, i = j + 1, j = i + 1);

        int d = n - s;
        if (d <= 0) {
            sb.append(-d + 1).append("/").append(i + d);
        } else {
            sb.append(d).append("/").append(j - (d - 1));
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
