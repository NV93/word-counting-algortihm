package lt.nevytas.service;

import lt.nevytas.utils.MyUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nevyt on 25-Oct-17.
 */

@Service
public class WordCountingService {

    private MyUtils utils = new MyUtils();
    private String fileContent;
    private Charset encoding = StandardCharsets.UTF_8;

    public String getTextFileContentInFolder(String pathToInputFolder) throws IOException {

        File folder = new File(pathToInputFolder);
        File[] listOfFiles = folder.listFiles();

        for (File file :
                listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                fileContent = utils.readFile(file.getCanonicalPath(), encoding);
            }
        }
        return fileContent;
    }

    public Map<String, Integer> countRepeatedWordOccurrence(String input) {
        ConcurrentHashMap<String, Integer> wordMap = new ConcurrentHashMap<>();
        String[] splitWordsArray = utils.splitString(input);

        for (String s : splitWordsArray) {
            wordMap.compute(s, (k, v) -> v == null ? 1 : v + 1);
        }

        Map<String, Integer> sortedMap = new TreeMap<>(wordMap);
        System.out.println(sortedMap);

        return sortedMap;
    }
}
