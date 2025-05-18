package com.example.modules.MP3s;

import com.example.myInts.MyModule;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3TitleMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Получить название трека из MP3";
    }

    @Override
    public void process(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();
            System.out.println("Название: " + tag.getFirst(FieldKey.TITLE));
        } catch (Exception e) {
            System.err.println("Ошибка при чтении MP3-тегов: " + e.getMessage());
        }
    }
}
