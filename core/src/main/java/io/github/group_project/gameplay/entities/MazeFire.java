package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeFire extends Entity {

    public MazeFire()
    {
        super();
        name = "Fire";
        SetSprite("fire.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
        hitbox.setPosition(position.x,position.y);
    }

    float delayTimer = 5;
    String movDir;
    float moveSpeed;

    public void setup(String direction)
    {
        movDir = direction;
    }

    @Override 
    public void Update()
    {
        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }
        if (hitbox == null) {
            hitbox = new Rectangle(position.x+16, position.y+16, 32, 28);
        } else {
            hitbox.setSize(32, 28);
            hitbox.setPosition(position.x+16, position.y+16);
        }

        moveSpeed = 180 * delta;
        if (IsCollidingWithEntityType(hitbox,"Player"))
        {
            int playerID = Engine.getFirstEntityIDOfType("Player");
            if (playerID > -1)
            {
                MazePlayer playerEntity = (MazePlayer) Engine.entities.get(playerID);
                if(playerEntity.defeated == false) {
                    playerEntity.Defeated();
                    Engine.spawnEntity(Defeat.class, 200, 200);
                }
            }
        }
        
        if (movDir == "right")
            position.add(moveSpeed,0);
        if (movDir == "left")
            position.add(-moveSpeed,0);
        if (movDir == "up")
            position.add(0,moveSpeed);
        if (movDir == "down")
            position.add(0,-moveSpeed);

        if(delayTimer > 0)
            delayTimer -= delta;
        else
            destroyed = true;

        super.Update();
    }
}
