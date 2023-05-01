package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[30];
    private FloatControl fc;
    public int volumeScale = 3;
    float volume;

    public Sound(){
        soundURL[0] = getClass().getResource("/res/sound/lobby1.wav");
        soundURL[1] = getClass().getResource("/res/sound/cursor.wav");
        soundURL[2] = getClass().getResource("/res/sound/gameover.wav");
        soundURL[3] = getClass().getResource("/res/sound/receivedamage.wav");
        soundURL[4] = getClass().getResource("/res/sound/card.wav");
        soundURL[5] = getClass().getResource("/res/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/res/sound/casino.wav");
        soundURL[7] = getClass().getResource("/res/sound/ballin.wav");
        soundURL[8] = getClass().getResource("/res/sound/Gate.wav");
        soundURL[9] = getClass().getResource("/res/sound/Boss.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
            checkVolume();
        } catch (Exception e) {
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
