package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import utils.java.InputStreamReader;

public class Q17478 {
    public static final String INTRO_MESSAGE = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
    public static final String QUESTION_MESSAGE = "\"재귀함수가 뭔가요?\"\n";
    public static final String THROW_MESSAGE_FORMAT = "%s\""
            + "잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"
            + "%s마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"
            + "%s그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    public static final String ANSWER_MESSAGE = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    public static final String CLOSE_MESSAGE = "라고 답변하였지.\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(br);

        sb.append(INTRO_MESSAGE);
        sb.append(QUESTION_MESSAGE);
        sb.append(String.format(THROW_MESSAGE_FORMAT, "", "", ""));
        int offset = INTRO_MESSAGE.length() + QUESTION_MESSAGE.length() + THROW_MESSAGE_FORMAT.length() - 6;
        for (int i = 0; i < n * 4; i += 4) {
            String underBar = "_".repeat(i + 4);

            sb.insert(offset, underBar + QUESTION_MESSAGE);
            offset += underBar.length() + QUESTION_MESSAGE.length();
            if (i + 4 != n * 4) {
                String throwMessage = String.format(THROW_MESSAGE_FORMAT, underBar, underBar, underBar);
                sb.insert(offset, throwMessage);
                offset += throwMessage.length();
            } else {
                sb.insert(offset, underBar + ANSWER_MESSAGE);
                offset += underBar.length() + ANSWER_MESSAGE.length();
            }
            sb.insert(offset, underBar + CLOSE_MESSAGE);
        }
        sb.append(CLOSE_MESSAGE);

        System.out.print(sb);
        br.close();
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
