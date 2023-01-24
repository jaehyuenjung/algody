package boj.fenwick_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q11658 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);

        int[][] arr = new int[n + 1][n + 1];
        int[][] fw = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = nextInt(br);
                update(fw, n, i, j, arr[i][j]);
            }
        }

        while (m-- > 0) {
            int w = nextInt(br);
            if (w == 0) {
                int y = nextInt(br), x = nextInt(br), c = nextInt(br);
                update(fw, n, y, x, c - arr[y][x]);
                arr[y][x] = c;
            } else {
                int y1 = nextInt(br), x1 = nextInt(br);
                int y2 = nextInt(br), x2 = nextInt(br);
                sb.append(sum(fw, y2, x2) - sum(fw, y2, x1 - 1) - sum(fw, y1 - 1, x2) + sum(fw, y1 - 1, x1 - 1))
                        .append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    static int sum(int[][] fw, int y, int x) {
        int rlt = 0;
        while (y > 0) {
            int xx = x;
            while (xx > 0) {
                rlt += fw[y][xx];
                xx -= (xx & -xx);
            }
            y -= (y & -y);
        }
        return rlt;
    }

    static void update(int[][] fw, int n, int y, int x, int d) {
        while (y <= n) {
            int xx = x;
            while (xx <= n) {
                fw[y][xx] += d;
                xx += (xx & -xx);
            }
            y += (y & -y);
        }
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
