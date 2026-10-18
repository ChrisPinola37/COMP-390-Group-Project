package io.github.group_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.entities.TestEntity;
import io.github.group_project.gameplay.entities.TestKey;
import io.github.group_project.gameplay.entities.TestBlock;
import io.github.group_project.gameplay.entities.TestGoalClosed;
import io.github.group_project.gameplay.entities.TestGoalOpen;
import io.github.group_project.gameplay.entities.TestVictory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    @Override
    public void create() {
        Sound background = Gdx.audio.newSound(Gdx.files.internal("background_music.mp3"));
        background.play(0.1f);

        levelOneLayout();
        Engine.spawnEntity(TestGoalClosed.class, 110, 80);
        Engine.spawnEntity(TestEntity.class, 112, 128);
        Engine.spawnEntity(TestKey.class, 480, 352);

    }

    public static void levelOneLayout() //Block layout for test level
    {
        Engine.spawnEntity(TestBlock.class, 0, 0);
        Engine.spawnEntity(TestBlock.class, 64, 0);
        Engine.spawnEntity(TestBlock.class, 128, 0);
        Engine.spawnEntity(TestBlock.class, 192, 0);
        Engine.spawnEntity(TestBlock.class, 256, 0);
        Engine.spawnEntity(TestBlock.class, 320, 0);
        Engine.spawnEntity(TestBlock.class, 384, 0);
        Engine.spawnEntity(TestBlock.class, 448, 0);
        Engine.spawnEntity(TestBlock.class, 512, 0);
        Engine.spawnEntity(TestBlock.class, 576, 0);
        Engine.spawnEntity(TestBlock.class, 0, 64);
        Engine.spawnEntity(TestBlock.class, 0, 128);
        Engine.spawnEntity(TestBlock.class, 0, 192);
        Engine.spawnEntity(TestBlock.class, 0, 256);
        Engine.spawnEntity(TestBlock.class, 0, 320);
        Engine.spawnEntity(TestBlock.class, 0, 384);
        Engine.spawnEntity(TestBlock.class, 0, 448);
        Engine.spawnEntity(TestBlock.class, 64, 448);
        Engine.spawnEntity(TestBlock.class, 128, 448);
        Engine.spawnEntity(TestBlock.class, 192, 448);
        Engine.spawnEntity(TestBlock.class, 256, 448);
        Engine.spawnEntity(TestBlock.class, 320, 448);
        Engine.spawnEntity(TestBlock.class, 384, 448);
        Engine.spawnEntity(TestBlock.class, 448, 448);
        Engine.spawnEntity(TestBlock.class, 512, 448);
        Engine.spawnEntity(TestBlock.class, 576, 448);
        Engine.spawnEntity(TestBlock.class, 576, 64);
        Engine.spawnEntity(TestBlock.class, 576, 128);
        Engine.spawnEntity(TestBlock.class, 576, 192);
        Engine.spawnEntity(TestBlock.class, 576, 256);
        Engine.spawnEntity(TestBlock.class, 576, 320);
        Engine.spawnEntity(TestBlock.class, 576, 384);
        Engine.spawnEntity(TestBlock.class, 192, 64);
        Engine.spawnEntity(TestBlock.class, 192, 128);
        Engine.spawnEntity(TestBlock.class, 192, 192);
        Engine.spawnEntity(TestBlock.class, 192, 256);
        Engine.spawnEntity(TestBlock.class, 192, 320);
        Engine.spawnEntity(TestBlock.class, 384, 128);
        Engine.spawnEntity(TestBlock.class, 384, 192);
        Engine.spawnEntity(TestBlock.class, 384, 256);
        Engine.spawnEntity(TestBlock.class, 384, 320);
        Engine.spawnEntity(TestBlock.class, 384, 384);
        Engine.spawnEntity(TestBlock.class, 448, 128);
        Engine.spawnEntity(TestBlock.class, 512, 256);
    }

    @Override
    public void render() {
        //Loop through every entity and update its logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
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
