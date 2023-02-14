package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Deque;
import java.util.LinkedList;
import utils.java.InputStreamReader;

public class Q3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int k = nextInt(br);

        boolean[][] apples = new boolean[n + 2][n + 2];
        while (k-- > 0) {
            apples[nextInt(br)][nextInt(br)] = true;
        }

        int l = nextInt(br);

        int[] directions = new int[10000 + 100 + 1];
        while (l-- > 0) {
            directions[nextInt(br)] = nextChar(br) == 'D' ? 1 : -1;
        }

        int answer = 0;
        int[][] visited = new int[n + 1][n + 1];

        Deque<Part> dq = new LinkedList<>();
        dq.add(new Part(1, 1, 2));
        dq.add(new Part(1, 1, 2));
        visited[1][1] = 2;

        while (dq.peekFirst().canNextMove(visited, n) && ++answer > 0) {
            Part head = dq.peekFirst();
            head.move();

            int d = directions[answer];
            if (d != 0) {
                head.d = (4 + (head.d - 1 + d)) % 4 + 1;
            }
            visited[head.r][head.c] = head.d;

            if (apples[head.r][head.c]) {
                apples[head.r][head.c] = false;
            } else {
                Part tail = dq.peekLast();

                visited[tail.r][tail.c] = 0;
                tail.move();
                tail.d = visited[tail.r][tail.c];
            }
        }

        System.out.println(answer + 1);
        br.close();
    }

    static class Part {
        private final static int[] DR = {0, -1, 0, 1, 0};
        private final static int[] DC = {0, 0, 1, 0, -1};

        int r, c, d;

        public Part(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void move() {
            this.r += DR[d];
            this.c += DC[d];
        }

        public boolean canNextMove(int[][] visited, int n) {
            int rr = r + DR[d];
            int cc = c + DC[d];
            return 0 < rr && rr <= n && 0 < cc && cc <= n && visited[rr][cc] == 0;
        }
    }

    static int nextChar(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) <= 32);
        return c;
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
