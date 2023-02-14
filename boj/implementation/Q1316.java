package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int[] arr = new int[26];

        int answer = n;
        for (int i = 1; i <= n; i++) {
            String str = nextLine(br);

            for (int j = 0; j < str.length(); j++) {
                char alpha = str.charAt(j);

                if (j > 0 && str.charAt(j - 1) != alpha
                        && arr[alpha - 'a'] == i) {
                    answer--;
                    break;
                }

                arr[alpha - 'a'] = i;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static String nextLine(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) <= 32);
        do sb.append((char) c);
        while ((c = reader.read()) > 32);
        return sb.toString();
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
