package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = nextInt(br);

        while (t-- > 0) {
            String commands = nextLine(br);
            int n = nextInt(br);

            int[] arr = new int[n];

            nextChar(br);
            if (n == 0) {
                nextChar(br);
            } else {
                for (int i = 0; i < n; i++) {
                    arr[i] = nextInt(br);
                }
            }

            int s = 0, e = n - 1, d = 0;
            int[] dd = {1, -1};

            boolean flag = true;
            for (int i = 0; i < commands.length() && flag; i++) {
                char command = commands.charAt(i);

                switch (command) {
                    case 'R':
                        d = (d + 1) % 2;

                        int tmp = s;
                        s = e;
                        e = tmp;
                        break;
                    case 'D':
                        if (d == 0 && s > e) {
                            flag = false;
                        } else if (d == 1 && s < e) {
                            flag = false;
                        }
                        s += dd[d];
                }
            }

            if (flag) {
                sb.append("[");
                if ((d == 0 && s <= e) || (d == 1 && e <= s)) {
                    while (s != e) {
                        sb.append(arr[s]).append(",");
                        s += dd[d];
                    }
                    sb.append(arr[s]);
                }
                sb.append("]\n");
            } else {
                sb.append("error\n");
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

    static int nextChar(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) <= 32);
        return c;
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while ((c = reader.read()) <= 32 || c >= 58);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
