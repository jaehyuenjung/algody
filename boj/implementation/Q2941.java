package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import utils.java.InputStreamReader;

public class Q2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        String str = br.readLine();

        int answer = 0;
        for (int i = 0, nxt = -1; i < str.length(); i = nxt, answer++) {
            nxt = i + 1;
            for (int j = 0; j < arr.length; j++) {
                int to = i + arr[j].length();
                if (to <= str.length()
                        && str.substring(i, to).equals(arr[j])) {
                    nxt = to;
                    break;
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
}
