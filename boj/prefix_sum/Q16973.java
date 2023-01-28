package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.PriorityQueue;
import utils.java.InputStreamReader;

public class Q16973 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int[][] dp = new int[n + 1][m + 1], arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = n * m;
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + nextInt(br);
            }
        }

        int h = nextInt(br);
        int w = nextInt(br);

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        PriorityQueue<Grid> q = new PriorityQueue<>();
        int sr = nextInt(br), sc = nextInt(br);
        int fr = nextInt(br), fc = nextInt(br);

        q.add(new Grid(sr, sc, 0));
        arr[sr][sc] = 0;

        int answer = -1;
        while (!q.isEmpty()) {
            Grid g = q.poll();

            if (g.compare(fr, fc)) {
                answer = g.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int r = g.r + dr[i], c = g.c + dc[i];

                if (!isValidGrid(dp, r, c, h, w, n, m)) {
                    continue;
                }

                int cnt = g.cnt + 1;

                if (arr[r][c] <= cnt) {
                    continue;
                }

                arr[r][c] = cnt;
                q.add(new Grid(r, c, cnt));
            }
        }

        System.out.println(answer);
        br.close();
    }

    static boolean isValidGrid(int[][] dp, int r, int c, int h, int w, int n, int m) {
        int[] pr = {0, 0, h - 1, h - 1};
        int[] pc = {0, w - 1, w - 1, 0};

        for (int i = 0; i < 4; i++) {
            int rr = r + pr[i], cc = c + pc[i];

            if (rr < 1 || rr > n || cc < 1 || cc > m) {
                return false;
            }
        }

        int sr = r + pr[0], sc = c + pc[0];
        int fr = r + pr[2], fc = c + pc[2];
        if (dp[fr][fc] - dp[fr][sc - 1] - dp[sr - 1][fc]
                + dp[sr - 1][sc - 1] > 0) {
            return false;
        }

        return true;
    }

    static class Grid implements Comparable<Grid> {
        int r, c, cnt;

        public Grid(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        public boolean compare(int r, int c) {
            return this.r == r && this.c == c;
        }

        @Override
        public int compareTo(Grid o) {
            return this.cnt - o.cnt;
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
