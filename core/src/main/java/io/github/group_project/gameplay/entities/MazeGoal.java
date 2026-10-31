package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.LevelManager;

public class MazeGoal extends Entity {

    public Boolean open = false;

    public MazeGoal()
    {
        super();
        name = "Goal";
        SetSprite("star_off.png");
    }

    public void Open()
    {
        open = true;
        SetSprite("star_on.png");
    }

    @Override
    public void Update()
    {
        if (open == true)
        {
            if (IsCollidingWithEntityType("Player"))
            {
                LevelManager.advanceLevel();
                destroyed = true;
                
            }
        }
        super.Update();
    }
}
