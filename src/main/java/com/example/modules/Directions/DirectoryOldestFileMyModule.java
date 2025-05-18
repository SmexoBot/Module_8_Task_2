package com.example.modules.Directions;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class DirectoryOldestFileMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Найти самый старый файл";
    }

    @Override
    public void process(File file) {
        File oldest = Arrays.stream(file.listFiles())
                .min(Comparator.comparing(File::lastModified))
                .orElse(null);
        System.out.println("Самый старый файл: " + (oldest != null ? oldest.getName() : "None"));
    }
}
