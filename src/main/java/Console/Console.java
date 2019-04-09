package Console;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements ConsoleReader {
    @Override
    public File consoleReader() {
        File pathFile = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String path = bufferedReader.readLine();
            if (path != null) {
                pathFile = new File(path);
            }
        }catch (
                IOException e) {
            e.printStackTrace();
    }
        return pathFile;
    }
}
