package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import utils.java.InputStreamReader;

public class Q1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = nextInt(br);

        while (t-- > 0) {
            int n = nextInt(br), m = nextInt(br);

            Integer[] arr = new Integer[n];
            Queue<Work> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt(br);
                q.add(new Work(i, arr[i]));
            }

            Arrays.sort(arr, Collections.reverseOrder());

            int maxIdx = 0;
            Work w = null;
            for (; w == null || w.oldNum != m; maxIdx++) {
                while (arr[maxIdx] != (w = q.poll()).priority) {
                    q.add(w);
                }
            }
            sb.append(maxIdx).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static class Work implements Comparable<Work> {
        int oldNum;
        int priority;

        public Work(int oldNum, int priority) {
            this.oldNum = oldNum;
            this.priority = priority;
        }

        @Override
        public int compareTo(Work o) {
            return o.priority - this.priority;
        }
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
