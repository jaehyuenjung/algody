package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Queue;
import utils.java.InputStreamReader;

public class Q2636 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);

        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
            }
        }

        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

        int time = 1, preCnt = 0, curCnt = 0;
        int[][] visited = new int[n][m];

        Queue<Integer> q = new LinkedList<>();

        do {
            preCnt = curCnt;
            curCnt = 0;

            q.add(0);
            while (!q.isEmpty()) {
                int p = q.remove();
                int r = p / m, c = p % m;

                if (visited[r][c] == time) {
                    continue;
                }

                visited[r][c] = time;

                if (map[r][c] == 1) {
                    map[r][c] = 0;
                    curCnt++;
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int rr = r + dr[i], cc = c + dc[i];

                    if (rr >= 0 && rr < n && cc >= 0 && cc < m && visited[rr][cc] < time) {
                        q.add(rr * m + cc);
                    }
                }
            }
        } while (curCnt > 0 && ++time > 0);

        System.out.println(sb.append(time - 1).append("\n").append(preCnt));
        br.close();
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
