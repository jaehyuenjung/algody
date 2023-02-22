package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] gears = new int[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = nextBitLine(br, 8);
        }

        int k = nextInt(br);

        int[] centerPos = new int[4];
        while (k-- > 0) {
            int i = nextInt(br) - 1, d = nextInt(br);

            int preL = (gears[i] >> (8 + (centerPos[i] - 2)) % 8) & 1;
            for (int j = i - 1, dd = d * -1; j >= 0; j--, dd *= -1) {
                int curR = (gears[j] >> (8 + (centerPos[j] + 2)) % 8) & 1;

                if ((preL ^ curR) == 0) {
                    break;
                }

                preL = (gears[j] >> (8 + (centerPos[j] - 2)) % 8) & 1;
                centerPos[j] = (8 + (centerPos[j] - dd)) % 8;
            }

            int preR = (gears[i] >> (8 + (centerPos[i] + 2)) % 8) & 1;
            for (int j = i + 1, dd = d * -1; j < 4; j++, dd *= -1) {
                int curL = (gears[j] >> (8 + (centerPos[j] - 2)) % 8) & 1;

                if ((preR ^ curL) == 0) {
                    break;
                }

                preR = (gears[j] >> (8 + (centerPos[j] + 2)) % 8) & 1;
                centerPos[j] = (8 + (centerPos[j] - dd)) % 8;
            }

            centerPos[i] = (8 + (centerPos[i] - d)) % 8;
        }

        int answer = 0;
        for (int i = 0, j = 1; i < 4; i++, j *= 2) {
            answer += ((gears[i] >> centerPos[i]) & 1) * j;
        }

        System.out.println(answer);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        boolean isNeg = false;
        while ((c = reader.read()) <= 32 || isSign(c)) isNeg = c == 45;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = reader.read()));
        return isNeg ? -n : n;
    }

    static int nextBitLine(Reader reader, int len) throws IOException {
        int n = 0;
        int c;
        while ((c = reader.read()) <= 32);
        for (int i = 0; i < len; i++, c = reader.read()) {
            n += (c - 48) << i;
        }
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }

    static boolean isSign(int c) {
        return c == 43 || c == 45;
    }
}
