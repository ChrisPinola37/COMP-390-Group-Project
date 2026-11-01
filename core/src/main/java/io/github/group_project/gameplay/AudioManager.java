package io.github.group_project.gameplay;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

//All audio will be controlled from this class 
//Previous audio work has been commented out (can be found in Main.java and MazeKey.java)
public class AudioManager {
    public static AudioManager instance = new AudioManager();

    private static final String TAG = AudioManager.class.getSimpleName();
    private final Map<String, Sound> soundMap = new HashMap<>();
    private final Music music;
    //loads all sound effects
    public AudioManager(){
        loadSounds();
        music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
    }
    //loads bg music
    private void loadSounds(){
        soundMap.put("pickup", Gdx.audio.newSound(Gdx.files.internal("explosion.mp3")));
        soundMap.put("star", Gdx.audio.newSound(Gdx.files.internal("star.wav")));
        soundMap.put("win", Gdx.audio.newSound(Gdx.files.internal("win.mp3")));


    }


    //Controller for playing sounds
    public void playSound(String soundName, float volume){
        Sound sound = soundMap.get(soundName);
        if (sound != null) {
            sound.play(volume);
        } else {
            Gdx.app.log(TAG, "Sound not found: " + soundName);
        }
    }


    //Controller for playing music
    public void playMusic(float volume){
        music.setVolume(volume);
        music.setLooping(true);
        music.play();
    }

    public void stopMusic() {
        if (music.isPlaying()) {
            music.stop();
        }
    }

    //Releases sound resources when no longer needed
    public void dispose(){
        for (Sound sound : soundMap.values()){
            sound.dispose();
        }
        music.dispose();
    }

    
}
