package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2573 {
    static int n, m, iceCnt;
    static int[] icePos = new int[10000 + 1], dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = nextInt(br);
        m = nextInt(br);
        iceCnt = 0;

        int maxIceSize = 0;

        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
                if (map[r][c] > 0) {
                    icePos[iceCnt++] = r * m + c;
                }
                maxIceSize = Math.max(maxIceSize, map[r][c]);
            }
        }

        int answer = 0;
        int meltedIceCnt = 0;

        int[][] visited = new int[n][m];
        int[] minusIce = new int[iceCnt];
        while (isOneIceGroup(answer, visited, map) && iceCnt != meltedIceCnt) {
            answer++;

            for (int i = 0; i < iceCnt; i++) {
                int r = icePos[i] / m, c = icePos[i] % m;
                minusIce[i] = 0;
                if (map[r][c] > 0) {
                    for (int j = 0; j < 4 && map[r][c] > 0; j++) {
                        int rr = r + dr[j], cc = c + dc[j];
                        if (rr >= 0 && rr < n && cc >= 0 && cc < m
                                && map[rr][cc] == 0) {
                            minusIce[i]++;
                        }
                    }
                }
            }

            for(int i = 0; i < iceCnt; i++){
                int r = icePos[i] / m, c = icePos[i] % m;
                if(map[r][c] > 0){
                    map[r][c] = Math.max(0, map[r][c] - minusIce[i]);

                    if(map[r][c] == 0){
                        meltedIceCnt++;
                    }
                }
            }

            for (int i = 0; i < iceCnt; i++) {
                int r = icePos[i] / m, c = icePos[i] % m;
                if (map[r][c] > 0) {
                    dfs(r, c, answer, visited, map);
                    break;
                }
            }
        }

        if (iceCnt == meltedIceCnt) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
        br.close();
    }

    static void dfs(int r, int c, int answer, int[][] visited, int[][] map) {
        visited[r][c] = answer;

        for (int i = 0; i < 4; i++) {
            int rr = r + dr[i], cc = c + dc[i];

            if (rr < 0 || rr >= n || cc < 0 || cc >= m
                    || map[rr][cc] == 0 || visited[rr][cc] == answer) {
                continue;
            }

            dfs(rr, cc, answer, visited, map);
        }
    }

    static boolean isOneIceGroup(int answer, int[][] visited, int[][] map) {
        for (int i = 0; i < iceCnt; i++) {
            int r = icePos[i] / m, c = icePos[i] % m;
            if (map[r][c] > 0 && visited[r][c] < answer) {
                return false;
            }
        }
        return true;
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
