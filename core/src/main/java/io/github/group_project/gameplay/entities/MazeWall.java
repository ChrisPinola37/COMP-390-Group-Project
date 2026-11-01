package io.github.group_project.gameplay.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeWall extends Entity {

    public MazeWall()
    {
        super();
        name = "Maze Wall";
        SetSprite("wall_vertical.png");
    }

    public void setup(Boolean isHorizontal)
    {
        if (isHorizontal)
        {
            SetSprite("wall_horizontal.png");
        }
        else
        {
            SetSprite("wall_vertical.png");
        }
    }
}
