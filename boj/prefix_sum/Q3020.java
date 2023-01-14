package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int h = nextInt(br);

        int[] cntT = new int[h + 1], cntB = new int[h + 1];

        for(int i = 0; i < n / 2; i++){
            cntB[nextInt(br)]++;
            cntT[nextInt(br)]++;
        }

        int[] dpT = new int[h + 1], dpB = new int[h + 1];
        dpT[h] = cntT[h]; dpB[h] = cntB[h];;
        for(int i = h; i > 1; i--){
            dpT[i - 1] = dpT[i] + cntT[i - 1];
            dpB[i - 1] = dpB[i] + cntB[i - 1];
        }

        int minCnt = n + 1, blcCnt = 0;
        for(int i = 1; i <= h; i++){
            int rlt = dpB[i] + dpT[h + 1 - i];
            if(rlt < minCnt){
                minCnt = rlt;
                blcCnt = 1;
            } else if(rlt == minCnt){
                blcCnt++;
            }
        }

        System.out.println(String.format("%d %d", minCnt, blcCnt));
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while((c = reader.read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while(isNumber(c = reader.read()));
        return n;
    }

    static boolean isNumber(int c){
        return 47 < c && c < 58;
    }
}
