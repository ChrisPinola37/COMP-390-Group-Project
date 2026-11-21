package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeSpikeTrap extends Entity {

    public MazeSpikeTrap()
    {
        super();
        name = "Spike Trap";
        SetSprite("spike_trap.png");
    }

    Random random = new Random();
    String movType;
    int movDir = random.nextInt(1);
    float moveSpeed;
    float delayTimer = 3;

    public void setup(Boolean isHorizontal)
    {
        if (isHorizontal)
            movType = "horizontal";
        else
            movType = "vertical";
    }

    @Override 
    public void Update()
    {
        if (hitbox == null) {
            hitbox = new Rectangle(position.x+2, position.y+2, 60, 60);
        } else {
            hitbox.setSize(60, 60);
            hitbox.setPosition(position.x+2, position.y+2);
        }
        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }
        moveSpeed = 240 * delta;

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

        if(delayTimer > 0)
            delayTimer -= delta;
        else
            delayTimer = 0;

        if (movType == "horizontal") {
            if (delayTimer == 0 && movDir == 1 && (isCollidingWithEntityTypeAtPoint(-moveSpeed,0,"Maze Wall") == false)) {
                position.add(-moveSpeed,0);
            }
            else if(movDir == 1 && isCollidingWithEntityTypeAtPoint(-moveSpeed,0,"Maze Wall") == true) {
            movDir = 0;
            if (delayTimer == 0)
                delayTimer = 3;
        }
            
        if (delayTimer == 0 && movDir == 0 && (isCollidingWithEntityTypeAtPoint(moveSpeed,0,"Maze Wall") == false)) { 
            position.add(moveSpeed,0);
            
        }
        else if(movDir == 0 && isCollidingWithEntityTypeAtPoint(moveSpeed,0,"Maze Wall") == true) {
                movDir = 1;
                if (delayTimer == 0)
                    delayTimer = 3;
        }
    } else {
        if (delayTimer == 0 && movDir == 1 && (isCollidingWithEntityTypeAtPoint(0,-moveSpeed,"Maze Wall") == false)) {
            position.add(0,-moveSpeed);
            
        }
        else if(movDir == 1 && isCollidingWithEntityTypeAtPoint(0,-moveSpeed,"Maze Wall") == true) {
            movDir = 0;
            if (delayTimer == 0)
                delayTimer = 3;
        }
            
        if (delayTimer == 0 && movDir == 0 && (isCollidingWithEntityTypeAtPoint(0,moveSpeed,"Maze Wall") == false)) { 
            position.add(0,moveSpeed);
            
        }
        else if(movDir == 0 && isCollidingWithEntityTypeAtPoint(0,moveSpeed,"Maze Wall") == true) {
                movDir = 1;
                if (delayTimer == 0)
                    delayTimer = 3;
        }
    }
        //position.set(Math.round(position.x),Math.round(position.y));

        super.Update();
    }
}
