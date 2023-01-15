package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q10211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = nextInt(br);

        while (t-- > 0) {
            int n = nextInt(br);

            int sum, answer;
            sum = answer = -1000;
            for (int i = 0; i < n; i++) {
                int vlu = nextInt(br);
                if(sum >= 0){
                    sum += vlu;
                }else{
                    sum = vlu;
                }
                answer = Math.max(answer, sum);
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
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
