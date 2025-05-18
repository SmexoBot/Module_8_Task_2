package com.example.modules.Directions;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class DirectorySizeMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Рассчитать размер каталога";
    }

    @Override
    public void process(File file) {
        try {
            long size = Files.walk(file.toPath())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            System.out.println("Размер: " + size + " bytes");
        } catch (IOException e) {
            System.err.println("Ошибка при расчете размера: " + e.getMessage());
        }
    }
}
