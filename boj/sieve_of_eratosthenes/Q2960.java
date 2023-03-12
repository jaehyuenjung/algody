package boj.sieve_of_eratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int k = nextInt(br);

        int answer = 0, cnt = 0;

        boolean[] visited = new boolean[n + 1];
        for (int i = 2; i <= n && cnt != k; i++) {
            if (visited[i]) {
                continue;
            }
            for (int j = 1; i * j <= n && cnt != k; j++) {
                if (!visited[i * j]) {
                    cnt++;
                    answer = i * j;
                }
                visited[i * j] = true;
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
