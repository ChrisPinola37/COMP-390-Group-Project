package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import io.github.group_project.engine.entity.Entity;

public class TestEntity extends Entity {
    String name = "Also A Test";

    public TestEntity()
    {
        super();
        SetSprite("freddy_yeah.png");
    }

    @Override 
    public void Update()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            position.add(1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            position.add(-1, 0);
        }
         if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            position.add(0, 1);
        }
         if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            position.add(0, -1);
        }
    }
}
