package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = nextInt(br);
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int answer = 0;

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, dfs(i, j, 1, n, m, dr, dc, visited, map));

                int cnt = 0;
                int sum = map[i][j];
                for (int k = 0; k < 4; k++) {
                    int rr = i + dr[k], cc = j + dc[k];

                    if (rr < 0 || rr >= n || cc < 0 || cc >= m) {
                        continue;
                    }

                    sum += map[rr][cc];
                    cnt++;
                }

                int maxRlt = 0;

                if (cnt == 4) {
                    for (int k = 0; k < 4; k++) {
                        int rr = i + dr[k], cc = j + dc[k];

                        maxRlt = Math.max(maxRlt, sum - map[rr][cc]);
                    }
                } else if (cnt == 3) {
                    maxRlt = sum;
                }

                answer = Math.max(answer, maxRlt);
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int dfs(int r, int c, int cnt, int n, int m, int[] dr, int[] dc, boolean[][] visited, int[][] map) {
        if (cnt == 4) {
            return map[r][c];
        }

        visited[r][c] = true;

        int maxRlt = 0;

        for (int i = 0; i < 4; i++) {
            int rr = r + dr[i], cc = c + dc[i];

            if (rr < 0 || rr >= n || cc < 0 || cc >= m || visited[rr][cc]) {
                continue;
            }

            maxRlt = Math.max(maxRlt, dfs(rr, cc, cnt + 1, n, m, dr, dc, visited, map));
        }

        visited[r][c] = false;

        return map[r][c] + maxRlt;
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
