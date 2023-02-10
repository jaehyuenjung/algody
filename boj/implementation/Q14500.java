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

        int[][] map = new int[n + 6][m + 6];
        for (int i = 3; i < n + 3; i++) {
            for (int j = 3; j < m + 3; j++) {
                map[i][j] = nextInt(br);
            }
        }

        int answer = 0;

        for (int i = 3; i < n + 3; i++) {
            for (int j = 3; j < m + 3; j++) {
                int sum = 0;

                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i + 2][j];
                sum += map[i + 3][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i][j + 1];
                sum += map[i][j + 2];
                sum += map[i][j + 3];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i + 1][j + 1];
                sum += map[i][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i + 2][j];
                sum += map[i + 2][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j + 1];
                sum += map[i + 1][j + 1];
                sum += map[i + 2][j + 1];
                sum += map[i + 2][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i][j + 1];
                sum += map[i][j + 2];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j + 2];
                sum += map[i][j + 1];
                sum += map[i][j + 2];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i][j + 1];
                sum += map[i + 1][j + 1];
                sum += map[i + 2][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i][j + 1];
                sum += map[i + 1][j];
                sum += map[i + 2][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j + 2];
                sum += map[i + 1][j + 2];
                sum += map[i + 1][j + 1];
                sum += map[i + 1][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i + 1][j + 2];
                sum += map[i + 1][j + 1];
                sum += map[i + 1][j];
                sum += map[i][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i + 1][j + 1];
                sum += map[i + 2][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j + 2];
                sum += map[i][j + 1];
                sum += map[i + 1][j + 1];
                sum += map[i + 1][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i + 2][j];
                sum += map[i + 1][j];
                sum += map[i + 1][j + 1];
                sum += map[i][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i][j + 1];
                sum += map[i + 1][j + 1];
                sum += map[i + 1][j + 2];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i][j + 1];
                sum += map[i][j + 2];
                sum += map[i + 1][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j + 1];
                sum += map[i + 1][j + 1];
                sum += map[i + 2][j + 1];
                sum += map[i + 1][j];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i + 1][j];
                sum += map[i + 1][j + 1];
                sum += map[i + 1][j + 2];
                sum += map[i][j + 1];
                answer = Math.max(answer, sum);

                sum = 0;
                sum += map[i][j];
                sum += map[i + 1][j];
                sum += map[i + 2][j];
                sum += map[i + 1][j + 1];
                answer = Math.max(answer, sum);
            }
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
