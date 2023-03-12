package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;
import utils.java.InputStreamReader;

public class Q2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Stack<Character> s = new Stack<>();

        int temp = 1, answer = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                temp *= 2;
                s.push(c);
            } else if (c == '[') {
                temp *= 3;
                s.push(c);
            } else if (c == ')') {
                if (s.isEmpty() || s.pop() != '(') {
                    answer = 0;
                    break;
                }
                if (str.charAt(i - 1) == '(') {
                    answer += temp;
                    temp /= 2;
                } else {
                    temp /= 2;
                }
            } else if (c == ']') {
                if (s.isEmpty() || s.pop() != '[') {
                    answer = 0;
                    break;
                }
                if (str.charAt(i - 1) == '[') {
                    answer += temp;
                    temp /= 3;
                } else {
                    temp /= 3;
                }
            }
        }

        if (!s.isEmpty()) {
            answer = 0;
        }

        System.out.println(answer);
        br.close();
    }
}
