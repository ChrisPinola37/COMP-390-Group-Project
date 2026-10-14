package io.github.group_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.entities.TestEntity;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    @Override
    public void create() {
        Engine.spawnEntity(TestEntity.class);
    }

    @Override
    public void render() {
        //Loop through every entity and update its logic
        for (int i = 0; i < Engine.entities.size(); i++)
        {
            Engine.entities.get(i).Update();
        }

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
