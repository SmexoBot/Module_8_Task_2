package com.example.modules.Images;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageSizeMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Получение размеров изображения";
    }

    @Override
    public void process(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            System.out.println("Размер: " + image.getWidth() + "x" + image.getHeight());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }
}
