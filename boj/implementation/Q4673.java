package boj.implementation;

public class Q4673 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        int[] nums = new int[10000 + 100];
        for(int i = 1; i <= 10000; i++){
            int sum = i;

            int p = i;
            while(p > 0){
                sum += p % 10;
                p /= 10;
            }

            nums[sum]++;

            if(nums[i] == 0)
                sb.append(i).append("\n");
        }

        System.out.print(sb);
    }
}
