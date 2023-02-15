package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.PriorityQueue;
import utils.java.InputStreamReader;

public class Q16236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);

        int curR = -1, curC = -1, curS = 2;

        int[][] map = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                map[r][c] = nextInt(br);

                if (map[r][c] == 9) {
                    curR = r;
                    curC = c;
                    map[r][c] = 0;
                }
            }
        }

        int answer = 0;
        int eatenCnt = 0;

        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, -1, 1, 0};

        boolean flag = false;
        int[][] visited = new int[n][n];
        do {
            flag = false;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    visited[r][c] = 400;
                }
            }

            PriorityQueue<Shark> q = new PriorityQueue<>();
            q.add(new Shark(curR, curC, 0));
            visited[curR][curC] = 0;

            while (!q.isEmpty()) {
                Shark cur = q.remove();

                if (map[cur.r][cur.c] != 0 && map[cur.r][cur.c] < curS) {
                    map[cur.r][cur.c] = 0;

                    curR = cur.r;
                    curC = cur.c;
                    answer += cur.t;

                    if (eatenCnt + 1 == curS) {
                        curS++;
                        eatenCnt = 0;
                    } else {
                        eatenCnt++;
                    }

                    flag = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int rr = cur.r + dr[i], cc = cur.c + dc[i];

                    if (rr < 0 || rr >= n || cc < 0 || cc >= n
                            || visited[rr][cc] <= cur.t + 1 || map[rr][cc] > curS) {
                        continue;
                    }

                    visited[rr][cc] = cur.t + 1;
                    q.add(new Shark(rr, cc, cur.t + 1));
                }
            }
        } while (flag);

        System.out.println(answer);
        br.close();
    }

    static class Shark implements Comparable<Shark> {
        int r, c, t;

        Shark(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.t == o.t) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.t - o.t;
        }
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
