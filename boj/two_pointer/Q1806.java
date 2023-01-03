package boj.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = nextInt(br);
        int s = nextInt(br);

        int[] arr = new int[n + 1];
        for(int i = 0; i < n; i++){
            arr[i] = nextInt(br);
        }

        int left, right, sum = arr[0], answer = n + 1;
        left = right = 0;
        while(left <= right && right < n){
            if(sum < s){
                sum += arr[++right];
            } else if(sum > s){
                answer = Math.min(answer, right - left + 1);
                sum -= arr[left++];
            } else{
                answer = Math.min(answer, right - left + 1);
                sum += arr[++right];
            }
        }

        System.out.println((answer == n + 1) ? 0 : answer);
        br.close();
    }

    static int nextInt(Reader reader) throws IOException {
        int n = 0;
        int c;
        while ((c = reader.read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber((c = reader.read())));
        return n;
    }

    static boolean isNumber(int c) {
        return 47 < c && c < 58;
    }
}
