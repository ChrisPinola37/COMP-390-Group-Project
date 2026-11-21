package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeEnemy extends Entity {

    public MazeEnemy()
    {
        super();
        name = "Enemy";
        SetSprite("enemy.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
    }

    Random random = new Random();
    int movDir = random.nextInt(3);
    int moveChance = 0;
    float moveSpeed;

    @Override 
    public void Update()
    {
        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }
        moveSpeed = 120 * delta;
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
        moveChance = random.nextInt(4);
        position.set(Math.round(position.x),Math.round(position.y));
        if ((movDir == 1 || movDir == 3) && ((position.x-2) % 64 == 0 && (position.y-2) % 64 == 0))
        {
            if((moveChance != 0) && (isCollidingWithEntityTypeAtPoint(moveSpeed,0,"Maze Wall") == false)) {
                position.add(moveSpeed,0);
                movDir = 0;
            }
            if((moveChance != 0) && (isCollidingWithEntityTypeAtPoint(-moveSpeed,0,"Maze Wall") == false)) {
                position.add(-moveSpeed,0);
                movDir = 2;
            }
        }
        else if ((position.x-2) % 64 == 0 && (position.y-2) % 64 == 0)
        {
            if((moveChance != 0) && (isCollidingWithEntityTypeAtPoint(0,moveSpeed,"Maze Wall") == false)) {
                position.add(0,moveSpeed);
                movDir = 1;
            }
            if((moveChance != 0) && (isCollidingWithEntityTypeAtPoint(0,-moveSpeed,"Maze Wall") == false)) {
                position.add(0,-moveSpeed);
                movDir = 3;
            }
        }
        
        if (movDir == 0)
        {
            if (isCollidingWithEntityTypeAtPoint(moveSpeed,0,"Maze Wall") == false)
                position.add(moveSpeed,0);
            else
                movDir = 2;
        }
        if (movDir == 1)
        {
            if (isCollidingWithEntityTypeAtPoint(0,-moveSpeed,"Maze Wall") == false)
                position.add(0,-moveSpeed);
            else
                movDir = 3;
        }
        if (movDir == 2)
        {
            if (isCollidingWithEntityTypeAtPoint(-moveSpeed,0,"Maze Wall") == false)
                position.add(-moveSpeed,0);
            else
                movDir = 0;
        }
        if (movDir == 3)
        {
            if (isCollidingWithEntityTypeAtPoint(0,moveSpeed,"Maze Wall") == false)
                position.add(0, moveSpeed);
            else
                movDir = 1;
        }
        position.set(Math.round(position.x),Math.round(position.y));

        super.Update();
    }
}
