package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);

        int[] nums = new int[8000 + 1];
        for (int i = 0; i < n; i++) {
            nums[nextInt(br) + 4000]++;
        }

        int totalCnt = 0, sum = 0;
        int midVal = -4001;
        int modeSq = 1, modeCnt = 0, modeVal = -4001;
        int minVal = 4001, maxVal = -4001;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }

            int num = i - 4000;

            totalCnt += nums[i];
            sum += num * nums[i];

            if (totalCnt > n / 2 && midVal == -4001) {
                midVal = num;
            }

            if (modeCnt < nums[i]) {
                modeSq = 1;
                modeCnt = nums[i];
                modeVal = num;
            } else if (modeCnt == nums[i] && ++modeSq == 2) {
                modeVal = num;
            }

            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        System.out.printf("%d\n%d\n%d\n%d\n", Math.round(sum / (double) n), midVal, modeVal, maxVal - minVal);
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
