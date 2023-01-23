package boj.segment_tree;

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

        int h = 1;
        while (h < n) {
            h <<= 1;
        }
        int ts = h * 2;

        int[][] seg = new int[ts][ts];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                update(seg, h, i, j, nextInt(br));
            }
        }

        while (m-- > 0) {
            int w = nextInt(br);
            if (w == 0) {
                int y = nextInt(br), x = nextInt(br), c = nextInt(br);
                update(seg, h, y, x, c);
            } else {
                int y1 = nextInt(br), x1 = nextInt(br);
                int y2 = nextInt(br), x2 = nextInt(br);
                sb.append(ySum(seg, h, y1, y2, 1, 1, h, x1, x2)).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    static int xSum(int[][] seg, int y, int rL, int rR, int x, int l, int r) {
        if (rL <= l && r <= rR) {
            return seg[y][x];
        } else if (rR < l || r < rL) {
            return 0;
        }
        int mid = (l + r) / 2;
        return xSum(seg, y, rL, rR, x * 2, l, mid) + xSum(seg, y, rL, rR, x * 2 + 1, mid + 1, r);
    }

    static int ySum(int[][] seg, int h, int rL, int rR, int y, int l, int r, int x1, int x2) {
        if (rL <= l && r <= rR) {
            return xSum(seg, y, x1, x2, 1, 1, h);
        } else if (rR < l || r < rL) {
            return 0;
        }
        int mid = (l + r) / 2;
        return ySum(seg, h, rL, rR, y * 2, l, mid, x1, x2) + ySum(seg, h, rL, rR, y * 2 + 1, mid + 1, r, x1,
                x2);
    }

    static void update(int[][] seg, int h, int y, int x, int d) {
        int i = y + h - 1, j = x + h - 1;
        seg[i][j] = d;
        while (j > 1) {
            j /= 2;
            seg[i][j] = seg[i][j * 2] + seg[i][j * 2 + 1];
        }
        while (i > 1) {
            j = x + h - 1;
            i /= 2;
            seg[i][j] = seg[i * 2][j] + seg[i * 2 + 1][j];
            while (j > 1) {
                j /= 2;
                seg[i][j] = seg[i][j * 2] + seg[i][j * 2 + 1];
            }
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
