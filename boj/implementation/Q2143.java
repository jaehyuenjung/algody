package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import utils.java.InputStreamReader;

public class Q2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = nextInt(br);
        int n = nextInt(br);

        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = nextInt(br);
        }

        int m = nextInt(br);

        int[] b = new int[m];
        for(int i = 0; i < m; i++){
            b[i] = nextInt(br);
        }

        HashMap<Integer, Integer> mapA = new HashMap<>();

        for(int i = 0, j = 0; i < n; i++){
            int sum = 0;
            for(int k = i; k < n; k++, j++){
                sum += a[k];
                Integer vlu = mapA.get(sum);
                if(vlu == null)mapA.put(sum, 1);
                else mapA.replace(sum, vlu + 1);
            }
        }

        long answer = 0;

        for(int i = 0, j = 0; i < m; i++){
            int sum = 0;
            for(int k = i; k < m; k++, j++){
                sum += b[k];
                int result = t - sum;
                Integer vlu = mapA.get(result);
                if(vlu != null){
                    answer += vlu;
                }
            }
        }

        System.out.println(answer);
        br.close();
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
