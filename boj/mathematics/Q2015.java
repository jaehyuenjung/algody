package boj.mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import utils.java.InputStreamReader;

public class Q2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int k = nextInt(br);

        long answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += nextInt(br);
            if(map.containsKey(sum - k)){
                answer += map.get(sum - k);
            }
            if(map.containsKey(sum)){
                map.put(sum, map.get(sum) + 1);
            }else{
                map.put(sum, 1);
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        boolean isNeg = false;
        while ((c = reader.read()) <= 32 || isSign(c)) isNeg = c == 45;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber((c = reader.read())));
        return isNeg ? -n : n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }

    static boolean isSign(int c){
        return c == 43 || c == 45;
    }
}
