package io.github.group_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

import io.github.group_project.gameplay.MazeGenerator;
import io.github.group_project.gameplay.LevelManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    @Override
    public void create() {
        Sound background = Gdx.audio.newSound(Gdx.files.internal("background_music.mp3"));
        background.play(0.1f);

        /* 
        MazeGenerator.generateMaze("level1.bin");
        MazeGenerator.generateMaze("level2.bin");
        MazeGenerator.generateMaze("level3.bin");
        MazeGenerator.generateMaze("level4.bin");
        MazeGenerator.generateMaze("level5.bin");
        */

        LevelManager.startLevel(0);
    }

    public void restartGame() {
        Engine.entities.clear();
        create();
    }

    @Override
    public void render() {
        Engine.updateEntities = true;
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartGame();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            Engine.debug = !Engine.debug;
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
        Engine.spriteBatch.end();
    }

    @Override
    public void dispose() {
        Engine.spriteBatch.dispose();
    }
}
