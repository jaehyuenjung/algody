package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import utils.java.InputStreamReader;

public class Q5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String arrStr = br.readLine();

            Deque<Integer> dq = new LinkedList<>();
            for (String s : arrStr.substring(1, arrStr.length() - 1).split(","))
                if (!s.equals(""))
                    dq.add(Integer.valueOf(s));

            boolean reverse = false, flag = true;

            for (int i = 0; i < commands.length(); i++) {
                char command = commands.charAt(i);

                if (command == 'R') {
                    reverse = !reverse;
                } else {
                    if (dq.size() == 0) {
                        flag = false;
                        break;
                    }

                    if (reverse) {
                        dq.removeLast();
                    } else {
                        dq.removeFirst();
                    }
                }
            }

            if(flag) {
                sb.append("[");
                while (!dq.isEmpty()) {
                    sb.append(reverse ? dq.removeLast() : dq.removeFirst());
                    if (dq.size() != 0) {
                        sb.append(',');
                    }
                }
                sb.append("]\n");
            }else{
                sb.append("error\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
