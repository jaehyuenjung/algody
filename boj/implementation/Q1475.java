package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import utils.java.InputStreamReader;

public class Q1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = new int[10];

        int n, sq = 0;
        while ((n = br.read() - 48) >= 0) {
            if (n == 6 || n == 9) {
                n = 6;
                if (nums[6] > nums[9]) {
                    n = 9;
                }
            }

            nums[n]++;

            if (nums[n] > sq) {
                sq++;
            }
        }

        System.out.println(sq);
        br.close();
    }
}
