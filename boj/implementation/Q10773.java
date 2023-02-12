package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = nextInt(br);

        int i = 0;
        int[] arr = new int[k];

        int sum = 0;
        while (k-- > 0) {
            int v = nextInt(br);

            if (v == 0) {
                sum -= arr[--i];
            } else {
                sum += v;
                arr[i++] = v;
            }
        }

        System.out.println(sum);
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
