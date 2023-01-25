package boj.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        List<Integer> sumA = getSumList(arrA, t);
        List<Integer> sumB = getSumList(arrB, t);

        sumA.sort(Comparator.naturalOrder());
        sumB.sort(Comparator.naturalOrder());

        int answer = 0;

        for (int v : sumA) {
            answer += getBoundary(sumB, t - v, false) - getBoundary(sumB, t - v, true);
        }

        System.out.println(answer);
        br.close();
    }

    static int getBoundary(List<Integer> arr, long target, boolean isLower) {
        int left = 0, right = arr.size() - 1;
        while (left <= right && left >= 0 && right < arr.size()) {
            int mid = (left + right) / 2;
            if (isLower) {
                if (arr.get(mid) >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (arr.get(mid) <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return left;
    }

    static List<Integer> getSumList(int[] arr, int t) {
        List<Integer> sum = new ArrayList<>(List.of(0));

        int low = 0, high = 0;
        int total = 0;
        while (low < arr.length) {
            total += arr[high++];

            if (total <= t) {
                sum.add(total);
            } else {
                low++;
                high = low;
                total = 0;
            }

            if (high == arr.length) {
                high = 0;
            }

            if ((low == 0 && high == 0) || high + 1 == low) {
                low++;
                high = low;
                total = 0;
            }
        }
        return sum;
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
