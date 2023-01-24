package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14468 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] from = new int[26];
        int[] to = new int[26];
        for (int i = 1; i <= 52; i++) {
            int pos = nextChar(br);
            if (from[pos] == 0) {
                from[pos] = i;
            } else {
                to[pos] = i;
            }
        }

        int answer = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (from[i] < from[j] && to[i] < to[j] && from[j] < to[i]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int nextChar(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) <= 32);
        return (c & 31) - 1;
    }
}
