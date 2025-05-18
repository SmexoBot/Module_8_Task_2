package com.example.modules.Directions;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class DirectoryListMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Перечислить содержимое каталога";
    }

    @Override
    public void process(File file) {
        Arrays.stream(file.listFiles())
                .forEach(f -> System.out.println(f.getName()));
    }
}
