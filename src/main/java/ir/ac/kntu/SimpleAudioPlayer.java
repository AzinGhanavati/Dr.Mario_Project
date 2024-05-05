package ir.ac.kntu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer {
    Long currentFrame;

    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;

    private String filePath;

    // constructor to initialize streams and clip
    public SimpleAudioPlayer(String filePath)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        this.filePath = filePath;
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        //start the clip
        clip.start();

        status = "play";
    }

    // Method to pause the audio
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
}