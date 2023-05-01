package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[30];
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
        soundURL[10] = getClass().getResource("/res/sound/Sweden.wav");
        soundURL[11] = getClass().getResource("/res/sound/Maze.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
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
}
