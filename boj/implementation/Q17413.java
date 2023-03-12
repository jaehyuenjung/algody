package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;
import utils.java.InputStreamReader;

public class Q17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        Stack<Character> s = new Stack<>();

        boolean isReverse = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                while (!s.isEmpty()) {
                    sb.append(s.pop());
                }
                sb.append(c);
            } else if (c == '<') {
                while (!s.isEmpty()) {
                    sb.append(s.pop());
                }
                isReverse = false;
                sb.append(c);
            } else if (c == '>') {
                isReverse = true;
                sb.append(c);
            } else {
                if (isReverse) {
                    s.push(c);
                } else {
                    sb.append(c);
                }
            }
        }

        while (!s.isEmpty()) {
            sb.append(s.pop());
        }

        System.out.println(sb);
        br.close();
    }
}
