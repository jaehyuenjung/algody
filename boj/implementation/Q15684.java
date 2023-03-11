package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q15684 {
    static int n, h, answer = 300;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = nextInt(br);
        int m = nextInt(br);
        h = nextInt(br);

        int[] map = new int[h + 1];
        while (m-- > 0) {
            int a = nextInt(br);
            int b = nextInt(br);
            map[a] += (1 << b);
        }

        if (getPositionChangeNum(map) > 3) {
            System.out.println(-1);
        } else {
            dfs(0, map);
            if (answer == 300) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        }
        br.close();
    }

    static int getPositionChangeNum(int[] map) {
        int num = 0;
        for (int j = 1; j < n && num <= 3; j++) {
            int cnt = 0;
            for (int i = 1; i <= h; i++) {
                if (((map[i] >> j) & 1) == 1) {
                    cnt++;
                }
            }
            if ((cnt & 1) == 1) {
                num++;
            }
        }
        return num;
    }

    static void dfs(int cnt, int[] map) {
        if (isPass(map)) {
            answer = cnt;
        }
        if (cnt >= 3 || answer <= cnt) {
            return;
        }

        for (int r = 1; r <= h; r++) {
            for (int c = 1; c < n; c++) {
                if (((map[r] >> c - 1) & 1) == 1 || ((map[r] >> c) & 1) == 1) {
                    continue;
                }

                map[r] += (1 << c);
                dfs(cnt + 1, map);
                map[r] -= (1 << c);
            }
        }
    }

    static boolean isPass(int[] map) {
        for (int i = 1; i <= n; i++) {
            int p = i;
            for (int r = 1; r <= h; r++) {
                if ((((map[r] >> p - 1) & 1) == 1)) {
                    p--;
                } else if ((((map[r] >> p) & 1) == 1)) {
                    p++;
                }
            }
            if (p != i) {
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
