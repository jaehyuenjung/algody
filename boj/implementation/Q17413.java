package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import utils.java.InputStreamReader;

public class Q17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();

        int e = 0;
        while(e < str.length) {
            char c = str[e];
            if(c == '<'){
                while(str[e++] != '>');
            } else {
                int s = e;
                while(e < str.length && str[e] != ' ' && str[e] != '<') e++;

                reverse(str, s, e - 1);

                if(e < str.length && str[e] == ' ')e++;
            }
        }

        System.out.println(str);
        br.close();
    }

    static void reverse(char[] str, int s, int e) {
        while(s < e) {
            char temp = str[s];
            str[s++] = str[e];
            str[e--] = temp;
        }
    }
}
