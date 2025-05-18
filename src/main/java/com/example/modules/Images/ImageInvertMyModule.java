package com.example.modules.Images;

import com.example.myInts.MyModule;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageInvertMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Инвертировать цвета изображения";
    }

    @Override
    public void process(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage inverted = new BufferedImage(
                    image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int rgba = image.getRGB(x, y);
                    Color color = new Color(rgba, true);
                    Color invertedColor = new Color(
                            255 - color.getRed(),
                            255 - color.getGreen(),
                            255 - color.getBlue(),
                            color.getAlpha());
                    inverted.setRGB(x, y, invertedColor.getRGB());
                }
            }
            ImageIO.write(inverted, "png", new File("inverted_" + file.getName()));
            System.out.println("Изображение обработано!");
        } catch (IOException e) {
            System.err.println("Ошибка при вставке изображения: " + e.getMessage());
        }
    }
}
