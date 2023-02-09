package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int curR = nextInt(br), curC = nextInt(br), d = nextInt(br);

        if(d % 2 == 1){
            d = (d + 2) % 4;
        }

        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        int answer = 1;

        while (true) {
            map[curR][curC] = 3;

            int nxtD = -1;
            for (int i = 1; i <= 4; i++) {
                int dd = (d + i) % 4;
                int rr = curR + dr[dd], cc = curC + dc[dd];
                if (map[rr][cc] == 0) {
                    nxtD = dd;
                    break;
                }
            }

            if (nxtD == -1) {
                int dd = (d + 2) % 4;
                int rr = curR + dr[dd], cc = curC + dc[dd];

                if (map[rr][cc] == 1) {
                    break;
                }

                curR = rr;
                curC = cc;
                continue;
            }

            d = nxtD;
            curR += dr[d];
            curC += dc[d];
            answer++;
        }

        System.out.println(answer);
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
