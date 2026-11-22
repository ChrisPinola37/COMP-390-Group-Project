package io.github.group_project.gameplay;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.HashMap;
import java.util.Map;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

import io.github.group_project.gameplay.MazeGenerator;
import io.github.group_project.gameplay.AudioManager;
import io.github.group_project.gameplay.LevelManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
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
        soundMap.put("lose", Gdx.audio.newSound(Gdx.files.internal("lose.wav")));
    
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
    public void playMusic(float startVolume){
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

    //allows for volume of music to be changed mid-game 
    private static float volume = 0.085f;

    public void changeVolume(float amount) {
        volume = com.badlogic.gdx.math.MathUtils.clamp(volume + amount, 0f, 1f);
        if (music != null) {
            music.setVolume(volume);
        }
    }

    public float getVolume() {
        return volume;
    }

    
}