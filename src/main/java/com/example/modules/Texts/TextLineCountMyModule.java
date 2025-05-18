package com.example.modules.Texts;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@Component
public class TextLineCountMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.getName().toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Подсчитайте количество строк в текстовом файле";
    }

    @Override
    public void process(File file) {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            System.out.println("Строк: " + lines.count());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
