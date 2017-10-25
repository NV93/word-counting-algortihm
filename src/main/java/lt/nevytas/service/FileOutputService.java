package lt.nevytas.service;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by nevyt on 25-Oct-17.
 */

public class FileOutputService implements  Runnable {
    @Autowired
    CountingAlgorithmService caService;

    private Character startingChar;
    private Character endingChar;
    private String outputDirectory;
    private String outputFilename;
    private Map<String, Integer> wordMap;

    public FileOutputService(Character startingChar, Character endingChar, String outputDirectory, String outputFilename, Map<String, Integer> wordMap) {
        this.startingChar = startingChar;
        this.endingChar = endingChar;
        this.outputDirectory = outputDirectory;
        this.outputFilename = outputFilename;
        this.wordMap = wordMap;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started ...");
        Path path = Paths.get(outputDirectory + outputFilename);
            try (Writer writer = Files.newBufferedWriter(path)) {
                wordMap.forEach((key, value) -> {
                    if(key.charAt(0)>= startingChar && key.charAt(0)<= endingChar)
                    try {
                        writer.write(key + " = " + value + System.lineSeparator());
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}

