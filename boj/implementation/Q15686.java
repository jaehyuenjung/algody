package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q15686 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int m = nextInt(br);

        int oneCnt = 0, twoCnt = 0;
        int[] oneArr = new int[2 * n], twoArr = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            int v = nextInt(br);
            if (v == 1) {
                oneArr[oneCnt++] = i;
            } else if (v == 2) {
                twoArr[twoCnt++] = i;
            }
        }

        System.out.println(dfs(0, 0, new boolean[twoCnt], n, m, oneArr, oneCnt, twoArr, twoCnt));
        br.close();
    }

    static int dfs(int idx, int cnt, boolean[] visited, int n, int m, int[] oneArr, int oneCnt, int[] twoArr,
                   int twoCnt) {
        if (cnt == m) {
            int sum = 0;
            for (int i = 0; i < oneCnt; i++) {
                int d = 50 * 2;

                for (int j = 0; j < twoCnt; j++) {
                    if(visited[j]) {
                        d = Math.min(d, Math.abs(oneArr[i] / n - twoArr[j] / n)
                                + Math.abs(oneArr[i] % n - twoArr[j] % n));
                    }
                }
                sum = sum + d;
            }
            return sum;
        }

        int result = 13 * 50 * 2;
        for (int i = idx; i <= twoCnt + cnt - m; i++) {
            visited[i] = true;
            result = Math.min(result, dfs(i + 1, cnt + 1, visited, n, m, oneArr, oneCnt, twoArr, twoCnt));
            visited[i] = false;
        }
        return result;
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
