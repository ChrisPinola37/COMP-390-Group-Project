package io.github.group_project;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.crypto.Data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import io.github.group_project.database.Database;
import io.github.group_project.database.DatabaseInitializer;
import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

import io.github.group_project.gameplay.MazeGenerator;
import io.github.group_project.gameplay.entities.PlayerNameManager;
import io.github.group_project.gameplay.AudioManager;
import io.github.group_project.gameplay.LevelManager;
import io.github.group_project.gameplay.LeaderboardManager;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputAdapter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    
    private boolean showLeaderboard = false;

    @Override
    public void create() {
       try {
            Engine.db = Database.production();
            DatabaseInitializer.initialize(Engine.db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    Gdx.input.setInputProcessor(new InputAdapter() {
    @Override
    public boolean keyTyped(char character) {
        if (!Engine.typingEnabled == true) {
            return false;
        }

        if (character == '\b') {
            if (!Engine.textEntry.isEmpty()) {
                Engine.textEntry = Engine.textEntry.substring(0, Engine.textEntry.length() - 1);
            }
        } else if (character == '\r' || character == '\n') {
            Engine.typingEnabled = false;
            Engine.typingEnded = true;
        } else if (Character.isLetterOrDigit(character) || character == ' ') {
            if (Engine.textEntry.length() < 15) {
                Engine.textEntry += character;
            }
        }

        return true;
    }
});
    

      

        //Sound background = Gdx.audio.newSound(Gdx.files.internal("background_music.mp3"));
        //background.play(0.1f);
        AudioManager.instance = new AudioManager();
        AudioManager.instance.playMusic(0.085f);
      

        
        MazeGenerator.generateMaze("level1.bin");
       // MazeGenerator.generateMaze("level2.bin");
      //  MazeGenerator.generateMaze("level3.bin");
     //   MazeGenerator.generateMaze("level4.bin");
       // MazeGenerator.generateMaze("level5.bin");
        

        LevelManager.startLevel(1);
        Engine.spawnEntity(PlayerNameManager.class, 0, 0);
    }
 //stops music from looping when restarting
    public void restartGame() {
    LeaderboardManager.resetScore();
    AudioManager.instance.stopMusic();
    Engine.entities.clear();
    create();
}

     

    @Override
    public void render() {

        Engine.updateEntities = true;
        if (!Engine.typingEnabled)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                restartGame();
            }
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
                Engine.debug = !Engine.debug;
            }
        }
        
        //Loop through every entity and update its logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            if (Engine.updateEntities == false)
            {
                break;
            }
            Engine.entities.get(i).Update();
        
        }
        Engine.clean();//Clean entities marked as destroyed

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Engine.spriteBatch.begin();
        //Loop through every entity and run their draw logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Engine.entities.get(i).Draw();
        }
        //Draw End (For entities who should render things on top)
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Engine.entities.get(i).DrawEnd();
        }
        Engine.spriteBatch.end();

    }
    

    @Override
    public void dispose() {
        Engine.spriteBatch.dispose();

        if (Engine.leaderboardFont != null) {
            Engine.leaderboardFont.dispose();
        }


        AudioManager.instance.dispose();

    }
}
