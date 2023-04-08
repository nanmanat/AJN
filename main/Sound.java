package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private String soundURL[] = new String[30];
    private FloatControl fc;
    public int volumeScale = 3;
    float volume;

    public Sound(){
<<<<<<< Updated upstream
        soundURL[0] = getClass().getResource("res/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("res/sound/cursor.wav");
=======
        soundURL[0] = new String("BlueBoyAdventure.wav");
        soundURL[1] = new String("cursor.wav");
>>>>>>> Stashed changes
    }

    public void setFile(int i){
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(soundURL[i]));
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
            checkVolume();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public void setVolumeScale(int volumeScale){
        this.volumeScale = volumeScale;
    }

    public void checkVolume(){
        switch (volumeScale) {
            case 0: volume = -80F; break;
            case 1: volume = -20F; break;
            case 2: volume = -12F; break;
            case 3: volume = -5F; break;
            case 4: volume = 1F; break;
            case 5: volume = 6F; break;
        }
        fc.setValue(volume);
    }
}
