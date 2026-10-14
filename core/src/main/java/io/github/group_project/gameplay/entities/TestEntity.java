package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.entity.Entity;

public class TestEntity extends Entity {

    public TestEntity()
    {
        super();
        name = "Also A Test";
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
        if (IsCollidingWithEntityType(""))
        {
            System.out.println("I just collided!");
        }
        else
        {
             System.out.println("Awww, no collision.");
        }
        super.Update();
    }
}
