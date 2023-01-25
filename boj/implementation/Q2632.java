package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2632 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = nextInt(br);
        int m = nextInt(br);
        int n = nextInt(br);

        int[] arrA = new int[m];
        for (int i = 0; i < m; i++) {
            arrA[i] = nextInt(br);
        }

        int[] arrB = new int[n];
        for (int i = 0; i < n; i++) {
            arrB[i] = nextInt(br);
        }

        int[] sumA = getSum(arrA);
        int[] sumB = getSum(arrB);

        int answer = 0;

        for (int i = 0; i <= t; i++) {
            answer += sumA[i] * sumB[t - i];
        }

        System.out.println(answer);
        br.close();
    }

    static int[] getSum(int[] arr) {
        int[] rlt = new int[1_000_000 + 1];
        rlt[0] = 1;

        for (int i = 0; i < arr.length; i++) {
            int p = i, sum = 0;
            for (int j = 0; j < arr.length - 1; j++, p = (p + 1) % arr.length) {
                sum += arr[p];
                rlt[sum]++;
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        rlt[sum]++;

        return rlt;
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
