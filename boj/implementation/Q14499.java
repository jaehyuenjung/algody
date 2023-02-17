package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14499 {
    public static final int[] OPPOSITE_SIDES = {5, 4, 3, 2, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);
        int m = nextInt(br);

        int curR = nextInt(br);
        int curC = nextInt(br);
        int curD = 2;
        int curP = 0;

        int k = nextInt(br);

        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
            }
        }

        int[] dr = {0, 0, -1, 1};
        int[] dc = {1, -1, 0, 0};

        int[] dice = {0, 1, 2, 3, 4, 5}, sides = new int[6];
        while (k-- > 0) {
            int d = nextInt(br) - 1;
            int nextR = curR + dr[d], nextC = curC + dc[d];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }

            curD = d;
            curR = nextR;
            curC = nextC;

            switch (curD) {
                case 0:
                    dice[0] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = dice[2];
                    dice[2] = curP;
                    break;
                case 1:
                    dice[0] = dice[2];
                    dice[2] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = curP;
                    break;
                case 2:
                    dice[0] = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[4];
                    dice[4] = curP;
                    break;
                case 3:
                    dice[0] = dice[4];
                    dice[4] = dice[5];
                    dice[5] = dice[1];
                    dice[1] = curP;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            curP = dice[0];

            if (map[curR][curC] == 0) {
                map[curR][curC] = sides[OPPOSITE_SIDES[curP]];
            } else {
                sides[OPPOSITE_SIDES[curP]] = map[curR][curC];
                map[curR][curC] = 0;
            }

            sb.append(sides[curP]).append("\n");
        }
        ;

        System.out.print(sb);
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
