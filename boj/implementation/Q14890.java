package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int l = nextInt(br) - 1;

        int[][] mapA = new int[n][n], mapB = new int[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                mapA[r][c] = nextInt(br);
                mapB[c][r] = mapA[r][c];
            }
        }

        int answer = 0;

        for (int r = 0; r < n; r++) {
            int cA = 0, cB = 0;
            int preLA = 1, preLB = 1;

            while (cA < n - 1 || cB < n - 1) {
                int nCA = cA + 1, nCB = cB + 1;

                if (nCA < n) {
                    if (Math.abs(mapA[r][cA] - mapA[r][nCA]) > 1) {
                        nCA = n;
                    } else if (mapA[r][cA] < mapA[r][nCA]) {
                        if (preLA > l) {
                            preLA = 1;
                        } else {
                            nCA = n;
                        }
                    } else if (mapA[r][cA] > mapA[r][nCA]) {
                        if (nCA + l < n && checkValidBridge(nCA, nCA + l, r, mapA, 1)) {
                            nCA += l;
                            preLA = 0;
                        } else {
                            nCA = n;
                        }
                    } else {
                        preLA++;
                    }
                    cA = nCA;
                }
                if (nCB < n) {
                    if (Math.abs(mapB[r][cB] - mapB[r][nCB]) > 1) {
                        nCB = n;
                    } else if (mapB[r][cB] < mapB[r][nCB]) {
                        if (preLB > l) {
                            preLB = 1;
                        } else {
                            nCB = n;
                        }
                    } else if (mapB[r][cB] > mapB[r][nCB]) {
                        if (nCB + l < n && checkValidBridge(nCB, nCB + l, r, mapB, 1)) {
                            nCB += l;
                            preLB = 0;
                        } else {
                            nCB = n;
                        }
                    } else {
                        preLB++;
                    }
                    cB = nCB;
                }
            }

            if (cA == n - 1) {
                answer++;
            }
            if (cB == n - 1) {
                answer++;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static boolean checkValidBridge(int s, int e, int r, int[][] map, int d) {
        int size = Math.abs(s - e);
        int sum = 0;
        for (int i = s; i != e && map[r][i] == map[r][i + d]; i += d, sum++);
        return size == sum;
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
