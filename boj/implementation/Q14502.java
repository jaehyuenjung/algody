package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14502 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int zeroCnt = 0, twoCnt = 0;
        int[] zeroArr = new int[n * m], twoArr = new int[n * m];
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = nextInt(br);
                if (map[i][j] == 0) {
                    zeroArr[zeroCnt++] = i * m + j;
                } else if (map[i][j] == 2) {
                    twoArr[twoCnt++] = i * m + j;
                }
            }
        }

        int answer = 0;
        boolean[][] visited = new boolean[n][m];

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < zeroCnt - 2; i++) {
            map[zeroArr[i] / m][zeroArr[i] % m] = 1;
            for (int j = i + 1; j < zeroCnt - 1; j++) {
                map[zeroArr[j] / m][zeroArr[j] % m] = 1;
                for (int k = j + 1; k < zeroCnt; k++) {
                    map[zeroArr[k] / m][zeroArr[k] % m] = 1;

                    for (int l = 0; l < twoCnt; l++) {
                        dfs(twoArr[l] / m, twoArr[l] % m, n, m, visited, map, dr, dc);
                    }

                    int cnt = 0;
                    for (int r = 0; r < n; r++) {
                        for (int c = 0; c < m; c++) {
                            if (visited[r][c]) {
                                cnt++;
                            }
                            visited[r][c] = false;
                        }
                    }

                    answer = Math.max(answer, zeroCnt - cnt - 3);

                    map[zeroArr[k] / m][zeroArr[k] % m] = 0;
                }
                map[zeroArr[j] / m][zeroArr[j] % m] = 0;
            }
            map[zeroArr[i] / m][zeroArr[i] % m] = 0;
        }

        System.out.println(answer);
        br.close();
    }

    static void dfs(int r, int c, int n, int m, boolean[][] visited, int[][] map, int[] dr, int[] dc) {
        for (int i = 0; i < 4; i++) {
            int rr = r + dr[i], cc = c + dc[i];

            if (rr < 0 || rr >= n || cc < 0 || cc >= m || visited[rr][cc] || map[rr][cc] != 0) {
                continue;
            }

            visited[rr][cc] = true;
            dfs(rr, cc, n, m, visited, map, dr, dc);
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
