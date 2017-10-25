package lt.nevytas.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by nevyt on 25-Oct-17.
 */
public class MyUtils {
    String fileContent;

    public String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        fileContent = fileContent + new String(encoded, encoding).toLowerCase();
        return fileContent;
    }

    public String[] splitString(String input){
        String[] words = input.split("\\W+");
        return words;
    }
}
