package utils.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;

public class InputStreamReader extends Reader {
    private final java.io.InputStreamReader streamReader;
    private final TimeChecker timeChecker;

    private boolean isNewOrEmptyFile;

    public InputStreamReader(InputStream in) throws IOException {
        String filePath = System.getProperty("sun.java.command").replaceAll("\\.", "/") + ".txt";

        File resourceFile = getAbsoluteResourceFile(filePath);

        if (isNewOrEmptyFile) {
            streamReader = new java.io.InputStreamReader(System.in);

            System.out.println("*** 입력 파일이 없거나 비어있어서 표준 입력 모드로 전환합니다. ***");
        } else {
            FileInputStream fileInputStream = new FileInputStream(resourceFile);
            streamReader = new java.io.InputStreamReader(fileInputStream);

            System.setIn(fileInputStream);
        }

        timeChecker = new TimeChecker();
        timeChecker.run();
    }

    private File getAbsoluteResourceFile(String filePath) throws IOException {
        File file = null;
        try {
            file = new File(getClass().getClassLoader().getResource(filePath).getPath());
        } catch (Exception e) {
            String resourcePath = System.getProperty("user.dir") + "/resources/";

            int sep = filePath.lastIndexOf("/");
            String dictPath = filePath.substring(0, sep);

            Files.createDirectories(new File(resourcePath + dictPath).toPath());
            Files.createFile(new File(resourcePath + filePath).toPath());

            System.out.println("*** 입력 파일이 없어서 생성합니다. ***");

            file = new File(resourcePath + filePath);
        } finally {
            if (file.length() == 0) {
                isNewOrEmptyFile = true;
            }
        }
        return file;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return streamReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        streamReader.close();

        timeChecker.stop();
        timeChecker.print();
    }
}
