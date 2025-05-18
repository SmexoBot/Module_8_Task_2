package com.example.modules.Images;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ExifInfoMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Извлечение данных EXIF";
    }

    @Override
    public void process(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            metadata.getDirectories().forEach(directory -> {
                directory.getTags().forEach(tag ->
                        System.out.println(tag.getTagName() + ": " + tag.getDescription()));
            });
        } catch (Exception e) {
            System.err.println("Ошибка при чтении EXIF: " + e.getMessage());
        }
    }
}
