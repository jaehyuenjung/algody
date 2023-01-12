package boj.two_pointer;

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

        Arrays.sort(dpA, (v1, v2) -> ((v1 > v2) ? 1 : (v1 < v2) ? -1 : 0));
        Arrays.sort(dpB, (v1, v2) -> ((v1 > v2) ? -1 : (v1 < v2) ? 1 : 0));

        long answer = 0;
        int left, right;
        left = right = 0;
        while(left < aLen && right < bLen){
            long vlu = dpA[left] + dpB[right];
            if(vlu < t){
                left++;
            }else if(vlu > t){
                right++;
            }else{
                long cntA, cntB;
                cntA = cntB = 0;

                long goalVluA = dpA[left];
                while(left < aLen && goalVluA == dpA[left]){cntA++; left++;}

                long goalVluB = dpB[right];
                while(right < bLen && goalVluB == dpB[right]){cntB++; right++;}

                answer += cntA * cntB;
            }
        }

        System.out.println(answer);
        br.close();
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
