package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import utils.java.InputStreamReader;

public class Q11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("<");

        int n = nextInt(br);
        int k = nextInt(br) - 1;

        List<Integer> arr = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }

        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(arr.remove(start = (start + k) % arr.size()));
        }
        sb.append(">");

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
