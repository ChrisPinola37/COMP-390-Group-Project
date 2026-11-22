package io.github.group_project.gameplay.entities;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.entity.Entity;

public class MazePlayer extends Entity {

    public MazePlayer()
    {
        super();
        name = "Player";
        SetSprite("player_left_0.png");
    }

    boolean defeated = false;
    float walkTimer;
    String walkDir = "right";
    float moveSpeed;
    float sprint = 1;

     // Speed power-up
private float speedBoostTimer = 0;

public void activateSpeedBoost() {
    speedBoostTimer = 5f;
}

    public void Defeated()
    {
        defeated = true;
        if (walkDir == "right")
            SetSprite("defeat_right.png");
        else
            SetSprite("defeat_left.png");
    }

    @Override 
    public void Update()
    {
        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }
        moveSpeed = 120 * delta;
        
    if (hitbox == null) {
        hitbox = new Rectangle(position.x+16, position.y, 32, 56);
    } else {
        hitbox.setSize(32, 56);
        hitbox.setPosition(position.x+16, position.y);
    }
    
    SetCollision(hitbox.width, hitbox.height);

        if (defeated == false) 
        {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            walkTimer = 0;
        // Update temporary speed boost
        if (speedBoostTimer > 0) {
            speedBoostTimer -= delta;
}

        // Keep the existing Shift sprint
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            sprint = 1.5f;
         else
        sprint = 1f;

         // Apply speed boost when active
        if (speedBoostTimer > 0) {
        sprint *= 1.5f;
}
        if (walkDir == "right")
            SetSprite("player_right_0.png");
        if (walkDir == "left")
            SetSprite("player_left_0.png");
        walkTimer += (6 * delta) * sprint;
        if (walkTimer > 2)
            walkTimer = 0;

        Vector2 movement = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            movement.add(moveSpeed * sprint, 0);
            if (walkTimer <= 1)
                SetSprite("player_right_1.png");
            else
                SetSprite("player_right_0.png");
            walkDir = "right";

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            movement.add(-moveSpeed * sprint, 0);
            if (walkTimer <= 1)
                SetSprite("player_left_1.png");
            else
                SetSprite("player_left_0.png");
            walkDir = "left";
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            movement.add(0, moveSpeed * sprint);
            if (walkDir == "left")
            {
                if (walkTimer <= 1)
                    SetSprite("player_left_1.png");
                else
                    SetSprite("player_left_0.png");
            }
            if (walkDir == "right")
            {
                if (walkTimer <= 1)
                    SetSprite("player_right_1.png");
                else
                    SetSprite("player_right_0.png");
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            movement.add(0, -moveSpeed * sprint);
            if (walkDir == "left")
            {
                if (walkTimer <= 1)
                    SetSprite("player_left_1.png");
                else
                    SetSprite("player_left_0.png");
            }
            if (walkDir == "right")
            {
                if (walkTimer <= 1)
                    SetSprite("player_right_1.png");
                else
                    SetSprite("player_right_0.png");
            }
        }

        hitbox.setPosition(position.x, position.y);
        if (isCollidingWithEntityTypeAtPoint(movement.x,0,"Maze Wall") == false && isCollidingWithEntityTypeAtPoint(movement.x,0,("Silver Block")) == false)
        {
            position.add(movement.x, 0);
        }
        if (isCollidingWithEntityTypeAtPoint(0,movement.y,("Maze Wall")) == false && isCollidingWithEntityTypeAtPoint(0,movement.y,("Silver Block")) == false)
        {
            position.add(0, movement.y);
        }
        position.set(Math.round(position.x),Math.round(position.y));
        position2.set(Math.round(position.x),Math.round(position.y));
    }
        super.Update();
    }
}
