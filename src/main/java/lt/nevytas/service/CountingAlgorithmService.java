package lt.nevytas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nevyt on 25-Oct-17.
 */
@Service
public class CountingAlgorithmService {

    @Autowired
    private WordCountingService wcService;
    private String workingDirPath = System.getProperty("user.dir");
    private String pathToInputFolder = workingDirPath + "\\src\\main\\resources\\resource-texts";

    public Map<String, Integer> getWordCountMap() throws IOException {
        String contextString = wcService.getTextFileContentInFolder(pathToInputFolder);
        Map<String, Integer> wordMap = wcService.countRepeatedWordOccurrence(contextString);
        System.out.println(workingDirPath);
        return wordMap;
    }
}
