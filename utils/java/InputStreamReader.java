package utils.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class InputStreamReader extends Reader {
    private final java.io.InputStreamReader streamReader;
    private final TimeChecker timeChecker;

    public InputStreamReader(InputStream in) throws IOException {
        String filePath = System.getProperty("sun.java.command").replaceAll("\\.", "/") + ".txt";

        FileInputStream fileInputStream = new FileInputStream(getAbsoluteResourceFile(filePath));
        streamReader = new java.io.InputStreamReader(fileInputStream);

        System.setIn(fileInputStream);

        timeChecker = new TimeChecker();
        timeChecker.run();
    }

    private File getAbsoluteResourceFile(String filePath) throws IOException {
        File file = new File(getClass().getClassLoader().getResource(filePath).getPath());
        if(!file.exists()){
            file.createNewFile();
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
