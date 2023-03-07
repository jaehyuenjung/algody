package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import utils.java.InputStreamReader;

public class Q15685 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);

        boolean[][] visited = new boolean[100 + 2][100 + 2];

        int[] generations = new int[n];
        List<List<Pair>> dragonDots = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int x = nextInt(br), y = nextInt(br), d = nextInt(br), g = nextInt(br);
            List<Pair> dots = new ArrayList<>();

            int xx = x, yy = y;
            switch (d) {
                case 0:
                    xx++;
                    break;
                case 1:
                    yy--;
                    break;
                case 2:
                    xx--;
                    break;
                case 3:
                    yy++;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                visited[x][y] = true;
            }
            if (xx >= 0 && xx <= 100 && yy >= 0 && yy <= 100) {
                visited[xx][yy] = true;
            }

            dots.add(new Pair(x, y));
            dots.add(new Pair(xx, yy));

            generations[i] = g;
            dragonDots.add(dots);
        }

        for (int i = 0; i < n; i++) {
            while (generations[i]-- > 0) {
                List<Pair> dots = dragonDots.get(i);
                int moveCnt = dots.size() - 1;

                Pair fromDot = dots.get(moveCnt);
                Pair toDot = new Pair(-fromDot.y, fromDot.x);

                int dX = fromDot.x - toDot.x;
                int dY = fromDot.y - toDot.y;

                for (int j = moveCnt - 1; j >= 0; j--) {
                    Pair p = dots.get(j);
                    int xx = -p.y + dX, yy = p.x + dY;

                    dots.add(new Pair(xx, yy));

                    if (xx >= 0 && xx <= 100 && yy >= 0 && yy <= 100) {
                        visited[xx][yy] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                if (visited[x][y] && visited[x + 1][y] && visited[x][y + 1] && visited[x + 1][y + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
        br.close();
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
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
