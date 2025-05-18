package com.example.modules.Texts;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TextCharFrequencyMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Вычислите частоту каждого символа";
    }

    @Override
    public void process(File file) {
        try {
            String content = Files.readString(file.toPath());
            Map<Character, Long> freq = content.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            freq.forEach((k, v) -> System.out.println(k + ": " + v));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
