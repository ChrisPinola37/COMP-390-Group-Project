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
        SetCollision(60, 60);
    }

    Random random = new Random();
    int movDir = random.nextInt(3);
    int moveChance = 0;

    @Override 
    public void Update()
    {
        if (IsCollidingWithEntityType("Player"))
        {
            int playerID = Engine.getFirstEntityIDOfType("Player");
            if (playerID > -1)
            {
                MazePlayer playerEntity = (MazePlayer) Engine.entities.get(playerID);
                playerEntity.Defeated();
                Engine.spawnEntity(Defeat.class, 200, 200);
            }
            destroyed = true;
        }
        moveChance = random.nextInt(2);
    
        if (movDir == 1 || movDir == 3)
        {
            if((moveChance == 0) && (isCollidingWithEntityTypeAtPoint(2,0,"Maze Wall") == false))
                movDir = 0;
            if((moveChance == 0) && (isCollidingWithEntityTypeAtPoint(-2,0,"Maze Wall") == false))
                movDir = 2;
        }
        else
        {
            if((moveChance == 0) && (isCollidingWithEntityTypeAtPoint(0,2,"Maze Wall") == false))
                movDir = 1;
            if((moveChance == 0) && (isCollidingWithEntityTypeAtPoint(0,-2,"Maze Wall") == false))
                movDir = 3;
        }
        
        if (movDir == 0)
        {
            if (isCollidingWithEntityTypeAtPoint(2,0,"Maze Wall") == false)
                position.add(2,0);
            else
                movDir = 2;
        }
        if (movDir == 1)
        {
            if (isCollidingWithEntityTypeAtPoint(0,-2,"Maze Wall") == false)
                position.add(0,-2);
            else
                movDir = 3;
        }
        if (movDir == 2)
        {
            if (isCollidingWithEntityTypeAtPoint(-2,0,"Maze Wall") == false)
                position.add(-2,0);
            else
                movDir = 0;
        }
        if (movDir == 3)
        {
            if (isCollidingWithEntityTypeAtPoint(0,2,"Maze Wall") == false)
                position.add(0,2);
            else
                movDir = 1;
        }

        super.Update();
    }
}
