package com.example.modules.Texts;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Component
public class TextWordCountMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчитайте количество слов";
    }

    @Override
    public void process(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long count = reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .count();
            System.out.println("Словы: " + count);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
