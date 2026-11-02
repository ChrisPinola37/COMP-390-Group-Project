package io.github.group_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

import io.github.group_project.gameplay.MazeGenerator;
import io.github.group_project.gameplay.LevelManager;
import io.github.group_project.gameplay.Leaderboard;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputAdapter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private BitmapFont scoreFont;
    private boolean showLeaderboard = false;
    private String typedName = "";
    private boolean enteringName = true;

    @Override
    public void create() {
        if (scoreFont == null) {
    scoreFont = new BitmapFont();

    }
    
    Gdx.input.setInputProcessor(new InputAdapter() {
    @Override
    public boolean keyTyped(char character) {
        if (!enteringName) {
            return false;
        }

        if (character == '\b') {
            if (!typedName.isEmpty()) {
                typedName = typedName.substring(0, typedName.length() - 1);
            }
        } else if (character == '\r' || character == '\n') {
            Leaderboard.setPlayerName(typedName);
            enteringName = false;
        } else if (Character.isLetterOrDigit(character) || character == ' ') {
            if (typedName.length() < 15) {
                typedName += character;
            }
        }

        return true;
    }
});
    

        Sound background = Gdx.audio.newSound(Gdx.files.internal("background_music.mp3"));
        background.play(0.1f);

        /* 
        MazeGenerator.generateMaze("level1.bin");
        MazeGenerator.generateMaze("level2.bin");
        MazeGenerator.generateMaze("level3.bin");
        MazeGenerator.generateMaze("level4.bin");
        MazeGenerator.generateMaze("level5.bin");
        */

        LevelManager.startLevel(1);
    }

    public void restartGame() {
    Leaderboard.resetScore();
    Engine.entities.clear();
    create();
}

    @Override
    public void render() {
       Engine.updateEntities = !enteringName;
        if (!enteringName && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
    restartGame();
}
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            Engine.debug = !Engine.debug;
        }
        if (!enteringName && Gdx.input.isKeyJustPressed(Input.Keys.L)) {
        showLeaderboard = !showLeaderboard;
}
 

        
        if (!enteringName) {
        //Loop through every entity and update its logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            if (Engine.updateEntities == false)
            {
                break;
            }
            Engine.entities.get(i).Update();
        }
    }
        Engine.clean();//Clean entities marked as destroyed

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Engine.spriteBatch.begin();
        //Loop through every entity and run their draw logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Engine.entities.get(i).Draw();
        }
        scoreFont.draw(
    Engine.spriteBatch,
    "Score: " + Leaderboard.getCurrentScore(),
    20,
    Gdx.graphics.getHeight() - 20
);
if (enteringName) {
    scoreFont.draw(
        Engine.spriteBatch,
        "Enter Your Name: " + typedName,
        250,
        300
    );

    scoreFont.draw(
        Engine.spriteBatch,
        "Press ENTER to start",
        250,
        270
    );
}

if (showLeaderboard) {
    scoreFont.draw(Engine.spriteBatch, "LEADERBOARD", 50, 420);

    java.util.ArrayList<String> scores = Leaderboard.getScores();

    if (scores.isEmpty()) {
        scoreFont.draw(Engine.spriteBatch, "No scores saved yet", 50, 390);
    }

    for (int i = 0; i < Math.min(5, scores.size()); i++) {
        scoreFont.draw(
            Engine.spriteBatch,
            (i + 1) + ". " + scores.get(i),
            50,
            390 - (i * 25)
        );
    }
}
        Engine.spriteBatch.end();

    }
    

    @Override
    public void dispose() {
        Engine.spriteBatch.dispose();
        if (scoreFont != null) {
    scoreFont.dispose();
}
    }
}
