package com.example.modules.MP3s;

import com.example.myInts.MyModule;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3DurationMyModule implements MyModule {

    @Override
    public boolean supportsFormat(File file) {
        return file.getName().toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Получите продолжительность MP3";
    }

    @Override
    public void process(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            AudioHeader header = audioFile.getAudioHeader();
            System.out.println("Продолжительность: " + header.getTrackLength() + "s");
        } catch (Exception e) {
            System.err.println("Ошибка при чтении длительности MP3: " + e.getMessage());
        }
    }
}
