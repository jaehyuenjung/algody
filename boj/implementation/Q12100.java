package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q12100 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = nextInt(br);

        int maxVal = 0;

        int[][] map = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                map[r][c] = nextInt(br);
                maxVal = Math.max(maxVal, map[r][c]);
            }
        }

        System.out.println(Math.max(maxVal, dfs(0, 0, map)));
        br.close();
    }

    static int dfs(int cnt, int maxRlt, int[][] map) {
        if (cnt == 5) {
            return maxRlt;
        }

        int[][] curMap = new int[n][n];

        int rlt = 0;
        for (int c = 0; c < n; c++) {
            int p = 0;
            curMap[p][c] = map[p][c];
            for (int r = 1; r < n; r++) {
                if (p < r) {
                    curMap[r][c] = 0;
                }
                if (map[r][c] == 0) {
                    continue;
                }

                if (curMap[p][c] == 0) {
                    curMap[p][c] = map[r][c];
                } else if (curMap[p][c] == map[r][c]) {
                    curMap[p][c] *= 2;
                    rlt = Math.max(rlt, curMap[p++][c]);
                } else {
                    curMap[++p][c] = map[r][c];
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, curMap));

        rlt = 0;
        for (int r = 0; r < n; r++) {
            int p = n - 1;
            curMap[r][p] = map[r][p];
            for (int c = n - 2; c >= 0; c--) {
                if (p > c) {
                    curMap[r][c] = 0;
                }
                if (map[r][c] == 0) {
                    continue;
                }

                if (curMap[r][p] == 0) {
                    curMap[r][p] = map[r][c];
                } else if (curMap[r][p] == map[r][c]) {
                    curMap[r][p] += map[r][c];
                    rlt = Math.max(rlt, curMap[r][p--]);
                } else {
                    curMap[r][--p] = map[r][c];
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, curMap));

        rlt = 0;
        for (int c = 0; c < n; c++) {
            int p = n - 1;
            curMap[p][c] = map[p][c];
            for (int r = n - 2; r >= 0; r--) {
                if (p > r) {
                    curMap[r][c] = 0;
                }
                if (map[r][c] == 0) {
                    continue;
                }

                if (curMap[p][c] == 0) {
                    curMap[p][c] = map[r][c];
                } else if (curMap[p][c] == map[r][c]) {
                    curMap[p][c] *= 2;
                    rlt = Math.max(rlt, curMap[p--][c]);
                } else {
                    curMap[--p][c] = map[r][c];
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, curMap));

        rlt = 0;
        for (int r = 0; r < n; r++) {
            int p = 0;
            curMap[r][p] = map[r][p];
            for (int c = 1; c < n; c++) {
                if (p < c) {
                    curMap[r][c] = 0;
                }
                if (map[r][c] == 0) {
                    continue;
                }

                if (curMap[r][p] == 0) {
                    curMap[r][p] = map[r][c];
                } else if (curMap[r][p] == map[r][c]) {
                    curMap[r][p] += map[r][c];
                    rlt = Math.max(rlt, curMap[r][p++]);
                } else {
                    curMap[r][++p] = map[r][c];
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, curMap));

        return maxRlt;
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
