package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q16234 {
    static int n, l, r;
    static int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = nextInt(br);
        l = nextInt(br);
        r = nextInt(br);

        Pair[][] map = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new Pair(nextInt(br), 1);
            }
        }

        int step = 0, answer = 0;
        int[][] visited = new int[n][n];

        boolean flag;
        do {
            step++;
            flag = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] != step) {
                        Pair p = new Pair(0, 0);
                        dfs(i, j, p, step, visited, map);

                        if (p.cnt != 1) {
                            flag = true;
                        }
                    }
                }
            }
        } while (flag && ++answer > 0);

        System.out.println(answer);
        br.close();
    }

    static void dfs(int i, int j, Pair p, int s, int[][] visited, Pair[][] map) {
        visited[i][j] = s;

        p.sum += map[i][j].getPopulation();
        p.cnt += 1;

        for (int k = 0; k < 4; k++) {
            int ii = i + di[k], jj = j + dj[k];

            if (ii < 0 || ii >= n || jj < 0 || jj >= n || visited[ii][jj] == s) {
                continue;
            }

            int d = Math.abs(map[i][j].getPopulation() - map[ii][jj].getPopulation());

            if (d < l || d > r) {
                continue;
            }

            dfs(ii, jj, p, s, visited, map);
        }

        map[i][j] = p;
    }

    static class Pair {
        int sum, cnt;

        public Pair(int sum, int cnt) {
            this.sum = sum;
            this.cnt = cnt;
        }

        public int getPopulation() {
            return sum / cnt;
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
