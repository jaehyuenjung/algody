package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int m = nextInt(br);

        int nums = 0;
        while (m-- > 0) {
            String cmd = nextLine(br);

            int x = 0;
            if (!("all".equals(cmd) || "empty".equals(cmd))) {
                x = nextInt(br);
            }

            switch (cmd) {
                case "add":
                    nums |= 1 << x;
                    break;
                case "remove":
                    nums &= ~(1 << x);
                    break;
                case "check":
                    sb.append((nums >> x) & 1).append("\n");
                    break;
                case "toggle":
                    nums ^= 1 << x;
                    break;
                case "all":
                    nums = (1 << 21) - 1;
                    break;
                case "empty":
                    nums = 0;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        System.out.print(sb);
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
