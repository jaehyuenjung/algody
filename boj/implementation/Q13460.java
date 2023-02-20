package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.PriorityQueue;
import utils.java.InputStreamReader;

public class Q13460 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int rr = -1, cr = -1, rb = -1, cb = -1;
        char[][] map = new char[n][m];
        for (int r = 0; r < n; r++) {
            String str = nextLine(br);
            for (int c = 0; c < m; c++) {
                map[r][c] = str.charAt(c);
                if (map[r][c] == 'R') {
                    rr = r;
                    cr = c;
                } else if (map[r][c] == 'B') {
                    rb = r;
                    cb = c;
                }
            }
        }

        int answer = -1;

        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
        boolean[][][][] visited = new boolean[n][m][n][m];

        PriorityQueue<Step> pq = new PriorityQueue<>();
        pq.add(new Step(rr, cr, rb, cb, 0));
        while (!pq.isEmpty() && answer == -1) {
            Step s = pq.remove();

            if (s.cnt >= 10) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nrr = s.rr, ncr = s.cr, nrb = s.rb, ncb = s.cb;

                int rd = 0;
                while (map[nrr + dr[d]][ncr + dc[d]] != '#' && map[nrr][ncr] != 'O') {
                    nrr += dr[d];
                    ncr += dc[d];
                    rd++;
                }

                int bd = 0;
                while (map[nrb + dr[d]][ncb + dc[d]] != '#' && map[nrb][ncb] != 'O') {
                    nrb += dr[d];
                    ncb += dc[d];
                    bd++;
                }

                if (map[nrb][ncb] == 'O') {
                    continue;
                } else if (map[nrr][ncr] == 'O') {
                    answer = s.cnt + 1;
                    break;
                }

                if (nrr == nrb && ncr == ncb) {
                    if (rd > bd) {
                        nrr -= dr[d];
                        ncr -= dc[d];
                    } else if (rd < bd) {
                        nrb -= dr[d];
                        ncb -= dc[d];
                    }
                }

                if (visited[nrr][ncr][nrb][ncb]) {
                    continue;
                }
                visited[nrr][ncr][nrb][ncb] = true;

                pq.add(new Step(nrr, ncr, nrb, ncb, s.cnt + 1));
            }
        }

        System.out.println(answer);
        br.close();
    }

    static class Step implements Comparable<Step> {
        int rr, cr, rb, cb, cnt;

        public Step(int rr, int cr, int rb, int cb, int cnt) {
            this.rr = rr;
            this.cr = cr;
            this.rb = rb;
            this.cb = cb;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Step o) {
            return cnt - o.cnt;
        }
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
        while ((c = reader.read()) <= 32)
            ;
        do {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        while (isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
