package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q15683 {
    static int n, m;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = nextInt(br);
        m = nextInt(br);

        int cctvCnt = 0, wallCnt = 0;
        int[] cctvPos = new int[8];

        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
                if (map[r][c] > 0 && map[r][c] < 6) {
                    cctvPos[cctvCnt++] = r * m + c;
                } else if (map[r][c] > 0 && map[r][c] == 6) {
                    wallCnt++;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];

        System.out.println(n * m - (cctvCnt + wallCnt + dfs(0, 0, cctvCnt, cctvPos, visited, map)));
        br.close();
    }

    static int dfs(int nextPos, int maxRlt, int cctvCnt, int[] cctvPos, boolean[][] visited, int[][] map) {
        if (nextPos == cctvCnt) {
            return maxRlt;
        }

        int r = cctvPos[nextPos] / m;
        int c = cctvPos[nextPos] % m;

        boolean[][] preVisited = copyVisited(visited);

        int rlt = 0;
        if (map[r][c] == 1) {
            for (int i = 0; i < 4; i++) {
                int cnt = 0;
                int rr = r + dr[i], cc = c + dc[i];

                while (rr >= 0 && rr < n && cc >= 0 && cc < m
                        && map[rr][cc] != 6) {
                    if (map[rr][cc] == 0 && !visited[rr][cc]) {
                        visited[rr][cc] = true;
                        cnt++;
                    }
                    rr += dr[i];
                    cc += dc[i];
                }

                rlt = Math.max(rlt, dfs(nextPos + 1, maxRlt + cnt, cctvCnt, cctvPos, visited, map));

                visited = copyVisited(preVisited);
            }
        } else if (map[r][c] == 2) {
            for (int i = 0; i < 2; i++) {
                int cnt = 0;
                for (int j = 0, p = i; j < 2; j++, p = (i + j * 2) % 4) {
                    int rr = r + dr[p], cc = c + dc[p];

                    while (rr >= 0 && rr < n && cc >= 0 && cc < m
                            && map[rr][cc] != 6) {
                        if (map[rr][cc] == 0 && !visited[rr][cc]) {
                            visited[rr][cc] = true;
                            cnt++;
                        }
                        rr += dr[p];
                        cc += dc[p];
                    }
                }

                rlt = Math.max(rlt, dfs(nextPos + 1, maxRlt + cnt, cctvCnt, cctvPos, visited, map));

                visited = copyVisited(preVisited);
            }
        } else if (map[r][c] == 5) {
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int rr = r + dr[i], cc = c + dc[i];

                while (rr >= 0 && rr < n && cc >= 0 && cc < m
                        && map[rr][cc] != 6) {
                    if (map[rr][cc] == 0 && !visited[rr][cc]) {
                        visited[rr][cc] = true;
                        cnt++;
                    }
                    rr += dr[i];
                    cc += dc[i];
                }
            }

            rlt = Math.max(rlt, dfs(nextPos + 1, maxRlt + cnt, cctvCnt, cctvPos, visited, map));

            visited = copyVisited(preVisited);
        } else {
            for (int i = 0; i < 4; i++) {
                int cnt = 0;
                for (int j = 0, p = i; j < map[r][c] - 1; j++, p = (p + 1) % 4) {
                    int rr = r + dr[p], cc = c + dc[p];

                    while (rr >= 0 && rr < n && cc >= 0 && cc < m
                            && map[rr][cc] != 6) {
                        if (map[rr][cc] == 0 && !visited[rr][cc]) {
                            visited[rr][cc] = true;
                            cnt++;
                        }
                        rr += dr[p];
                        cc += dc[p];
                    }
                }

                rlt = Math.max(rlt, dfs(nextPos + 1, maxRlt + cnt, cctvCnt, cctvPos, visited, map));

                visited = copyVisited(preVisited);
            }
        }

        return rlt;
    }

    static boolean[][] copyVisited(boolean[][] visited) {
        boolean[][] temp = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                temp[r][c] = visited[r][c];
            }
        }
        return temp;
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
