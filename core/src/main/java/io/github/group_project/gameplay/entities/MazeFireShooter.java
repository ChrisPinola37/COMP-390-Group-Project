package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeFireShooter extends Entity {

    public MazeFireShooter()
    {
        super();
        name = "Fire Shooter";
        SetSprite("fire_shooter.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
    }

    float delayTimer = 5;

    @Override 
    public void Update()
    {
        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }

        if(delayTimer > 0)
            delayTimer -= delta;
        else {
            Engine.spawnEntity(MazeFire.class, position.x, position.y).setup("right");
            Engine.spawnEntity(MazeFire.class, position.x, position.y).setup("left");
            Engine.spawnEntity(MazeFire.class, position.x, position.y).setup("up");
            Engine.spawnEntity(MazeFire.class, position.x, position.y).setup("down");
            delayTimer = 5;
        }

        super.Update();
    }
}
