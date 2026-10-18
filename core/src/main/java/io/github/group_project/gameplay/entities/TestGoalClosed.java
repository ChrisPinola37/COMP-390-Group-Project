package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class TestGoalClosed extends Entity {

    public TestGoalClosed()
    {
        super();
        name = "Goal Closed";
        SetSprite("star_off.png");
    }

    @Override 
    public void Update()
    {
        if (IsCollidingWithEntityType("Goal Open"))
        {
            destroyed = true;
        }
        super.Update();
    }
}
