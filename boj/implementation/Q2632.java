package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
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

        Map<Integer, Integer> mapA = getMap(arrA, t);
        Map<Integer, Integer> mapB = getMap(arrB, t);

        int answer = 0;

        for (int v : mapA.keySet()) {
            if(mapB.containsKey(t - v)) {
                answer += mapA.get(v) * mapB.get(t - v);
            }
        }

        if(mapA.containsKey(t)){
            answer += mapA.get(t);
        }
        if(mapB.containsKey(t)){
            answer += mapB.get(t);
        }

        System.out.println(answer);
        br.close();
    }

    static Map<Integer, Integer> getMap(int[] arr, int t) {
        Map<Integer, Integer> map = new HashMap<>();

        int low = 0, high = 0;
        int total = 0;
        while (low < arr.length) {
            total += arr[high++];

            if (total <= t) {
                if(map.containsKey(total)){
                    map.put(total, map.get(total) + 1);
                }else{
                    map.put(total, 1);
                }
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
        return map;
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
