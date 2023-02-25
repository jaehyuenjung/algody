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

        int[][] map = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                map[r][c] = nextInt(br);
            }
        }

        System.out.println(dfs(0, 0, map));
        br.close();
    }

    static int dfs(int cnt, int maxRlt, int[][] map) {
        if (cnt == 5) {
            return maxRlt;
        }

        int[][] preMap = copyMap(map);

        int rlt = 0;
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n - 1; r++) {
                if (map[r][c] == 0) {
                    continue;
                }
                for (int k = r + 1; k < n; k++) {
                    if (map[k][c] == 0) {
                        continue;
                    }
                    if (map[r][c] == map[k][c]) {
                        map[r][c] *= 2;
                        map[k][c] = 0;
                        break;
                    } else {
                        break;
                    }
                }
            }

            for (int r = 0; r < n; r++) {
                rlt = Math.max(rlt, map[r][c]);

                if (r != 0 && map[r][c] != 0) {
                    int d = 1;
                    while (r - 1 * (d - 1) > 0 && map[r - 1 * d][c] == 0) {
                        d++;
                    }

                    if (d - 1 > 0) {
                        map[r - 1 * (d - 1)][c] = map[r][c];
                        map[r][c] = 0;
                    }
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, map));

        map = copyMap(preMap);

        rlt = 0;
        for (int r = 0; r < n; r++) {
            for (int c = n - 1; c > 0; c--) {
                if (map[r][c] == 0) {
                    continue;
                }
                for (int k = c - 1; k >= 0; k--) {
                    if (map[r][k] == 0) {
                        continue;
                    }
                    if (map[r][c] == map[r][k]) {
                        map[r][c] *= 2;
                        map[r][k] = 0;
                        break;
                    } else {
                        break;
                    }
                }
            }

            for (int c = n - 1; c >= 0; c--) {
                rlt = Math.max(rlt, map[r][c]);

                if (c != n - 1 && map[r][c] != 0) {
                    int d = 1;
                    while (c + 1 * (d - 1) < n - 1 && map[r][c + 1 * d] == 0) {
                        d++;
                    }

                    if (d - 1 > 0) {
                        map[r][c + 1 * (d - 1)] = map[r][c];
                        map[r][c] = 0;
                    }
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, map));

        map = copyMap(preMap);

        rlt = 0;
        for (int c = 0; c < n; c++) {
            for (int r = n - 1; r > 0; r--) {
                if (map[r][c] == 0) {
                    continue;
                }
                for (int k = r - 1; k >= 0; k--) {
                    if (map[k][c] == 0) {
                        continue;
                    }
                    if (map[r][c] == map[k][c]) {
                        map[r][c] *= 2;
                        map[k][c] = 0;
                        break;
                    } else {
                        break;
                    }
                }
            }

            for (int r = n - 1; r >= 0; r--) {
                rlt = Math.max(rlt, map[r][c]);

                if (r != n - 1 && map[r][c] != 0) {
                    int d = 1;
                    while (r + 1 * (d - 1) < n - 1 && map[r + 1 * d][c] == 0) {
                        d++;
                    }

                    if (d - 1 > 0) {
                        map[r + 1 * (d - 1)][c] = map[r][c];
                        map[r][c] = 0;
                    }
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, map));

        map = copyMap(preMap);

        rlt = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n - 1; c++) {
                if (map[r][c] == 0) {
                    continue;
                }
                for (int k = c + 1; k < n; k++) {
                    if (map[r][k] == 0) {
                        continue;
                    }
                    if (map[r][c] == map[r][k]) {
                        map[r][c] *= 2;
                        map[r][k] = 0;
                        break;
                    } else {
                        break;
                    }
                }
            }

            for (int c = 0; c < n; c++) {
                rlt = Math.max(rlt, map[r][c]);

                if (c != 0 && map[r][c] != 0) {
                    int d = 1;
                    while (c - 1 * (d - 1) > 0 && map[r][c - 1 * d] == 0) {
                        d++;
                    }

                    if (d - 1 > 0) {
                        map[r][c - 1 * (d - 1)] = map[r][c];
                        map[r][c] = 0;
                    }
                }
            }
        }
        maxRlt = Math.max(maxRlt, dfs(cnt + 1, rlt, map));

        map = copyMap(preMap);

        return maxRlt;
    }

    static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                tempMap[r][c] = map[r][c];
            }
        }

        return tempMap;
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
