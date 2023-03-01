package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q17144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);
        int t = nextInt(br);

        int uR = 0, uC = 0, dR = 0, dC = 0;
        int[][] map = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                map[r][c] = nextInt(br);
                if (map[r][c] == -1) {
                    if (uR == 0) {
                        uR = r;
                        uC = c;
                    } else {
                        dR = r;
                        dC = c;
                    }
                    map[r][c] = 0;
                }
            }
        }

        int[] uDr = {-1, 0, 1, 0}, uDc = {0, 1, 0, -1}, dDr = {1, 0, -1, 0}, dDc = {0, 1, 0, -1};

        while (t-- > 0) {
            int[][] nextMap = new int[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (map[r][c] == 0) {
                        continue;
                    }
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        int rr = r + uDr[i], cc = c + uDc[i];

                        if (rr < 0 || rr >= n || cc < 0 || cc >= m
                                || (rr == uR && cc == uC) || (rr == dR && cc == dC)) {
                            continue;
                        }

                        cnt++;
                        nextMap[rr][cc] += map[r][c] / 5;
                    }
                    nextMap[r][c] += map[r][c] - map[r][c] / 5 * cnt;
                }
            }
            map = nextMap;

            map[uR][uC] = 0;
            map[dR][dC] = 0;

            int preURr = uR, preUCc = uC;
            int preDRr = dR, preDCc = dC;
            for (int i = 0; i < 4; i++) {
                int curURr = preURr + uDr[i], curUCc = preUCc + uDc[i];
                int curDRr = preDRr + dDr[i], curDCc = preDCc + dDc[i];

                while ((curURr >= 0 && curURr < dR && curUCc >= 0 && curUCc < m)
                        || (curDRr >= dR && curDRr < n && curDCc >= 0 && curDCc < m)) {
                    if (curURr >= 0 && curURr < dR && curUCc >= 0 && curUCc < m) {
                        if (curURr == uR && curUCc == uC) {
                            map[preURr][preUCc] = 0;
                        } else {
                            map[preURr][preUCc] = map[curURr][curUCc];
                            map[curURr][curUCc] = 0;
                        }

                        preURr = curURr;
                        preUCc = curUCc;
                        curURr = preURr + uDr[i];
                        curUCc = preUCc + uDc[i];
                    }
                    if (curDRr >= dR && curDRr < n && curDCc >= 0 && curDCc < m) {
                        if (curDRr == dR && curDCc == dC) {
                            map[preDRr][preDCc] = 0;
                        } else {
                            map[preDRr][preDCc] = map[curDRr][curDCc];
                            map[curDRr][curDCc] = 0;
                        }

                        preDRr = curDRr;
                        preDCc = curDCc;
                        curDRr = preDRr + dDr[i];
                        curDCc = preDCc + dDc[i];
                    }
                }
            }

            map[uR][uC] = 0;
            map[dR][dC] = 0;
        }

        int answer = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                answer += map[r][c];
            }
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

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }

    static boolean isSign(int c) {
        return c == 43 || c == 45;
    }
}
