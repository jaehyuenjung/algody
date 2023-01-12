package boj.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import utils.java.InputStreamReader;

public class Q2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = nextInt(br);
        int n = nextInt(br);

        int aLen = 0;
        long[] a = new long[n];
        for(int i = 0; i < n; i++){
            a[i] = nextInt(br);
            aLen += n - i;
        }

        int m = nextInt(br);

        int bLen = 0;
        long[] b = new long[m];
        for(int i = 0; i < m; i++){
            b[i] = nextInt(br);
            bLen += m - i;
        }

        Long[] dpA = new Long[aLen], dpB = new Long[bLen];
        init(a, dpA);
        init(b, dpB);

        Arrays.sort(dpB, (v1, v2) -> ((v1 > v2) ? 1 : (v1 < v2) ? -1 : 0));

        long answer = 0;
        for(int i = 0; i < aLen; i++){
            int low = getBoundary(dpB, t - dpA[i], true);
            int high = getBoundary(dpB, t - dpA[i], false);
            answer += high - low;
        }

        System.out.println(answer);
        br.close();
    }

    static int getBoundary(Long[] arr, long target, boolean isLower){
        int left = 0, right = arr.length - 1;
        while(left <= right && left >= 0 && right < arr.length){
            int mid = (left + right) / 2;
            if(isLower){
                if(arr[mid] >= target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(arr[mid] <= target){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return left;
    }

    static void init(long[] arr, Long[] dpArr) {
        int len = arr.length;
        for(int i = 0, j = 0; i < len; i++){
            dpArr[j++] = arr[i];
            for(int k = i + 1; k < len; k++, j++){
                dpArr[j] = dpArr[j - 1] + arr[k];
            }
        }
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        boolean isNeg = false;
        while((c = reader.read()) <= 32 || isSign(c)) isNeg = c == 45;
        do n = (n << 3) + (n << 1) + (c & 15);
        while(isNumber(c = reader.read()));
        return isNeg ? -n : n;
    }

    static boolean isNumber(int c){
        return 47 < c && c < 58;
    }

    static boolean isSign(int c){
        return c == 43 || c == 45;
    }
}
