package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class TestGoalOpen extends Entity {

    public TestGoalOpen()
    {
        super();
        name = "Goal Open";
        SetSprite("star_on.png");
    }

    @Override 
    public void Update()
    {
        if (IsCollidingWithEntityType("Player"))
        {

            Engine.spawnEntity(TestVictory.class, 200, 200);
            destroyed = true;
            
        }
        super.Update();
    }
}
