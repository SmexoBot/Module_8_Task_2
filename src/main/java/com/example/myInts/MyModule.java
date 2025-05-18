package com.example.myInts;

import java.io.File;

public interface MyModule {
    boolean supportsFormat(File file);
    String getDescription();
    void process(File file);
}
