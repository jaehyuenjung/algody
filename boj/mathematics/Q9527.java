package boj.mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q9527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long start = nextLong(br);
        long end = nextLong(br);

        long answer = 0;

        long two = 1;
        while(start <= end){
            while((end & 1) == 0 && start <= end){
                answer += calc(end--, two);
            }

            if(start > end) break;

            while((start & 1) == 1 && start <= end){
                answer += calc(start++, two);
            }

            end = end >> 1;
            start = start >> 1;

            answer += (end - start + 1) * two;
            two *= 2;
        }

        System.out.println(answer);
        br.close();
    }

    static long calc(long v, long two){
        long sum = 0;
        do{
            sum += (v & 1) * two;
        }while((v = v >> 1) > 0);
        return sum;
    }

    static long nextLong(Reader reader) throws IOException {
        long n = 0;
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
