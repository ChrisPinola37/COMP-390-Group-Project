package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.entity.Entity;

public class TestEntity extends Entity {

    public TestEntity()
    {
        super();
        name = "Player";
        SetSprite("player_left_0.png");
    }

    float walkTimer;
    String walkDir = "right";

    @Override 
    public void Update()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            walkTimer = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            walkTimer = 0;
        if (walkDir == "right")
            SetSprite("player_right_0.png");
        if (walkDir == "left")
            SetSprite("player_left_0.png");
        walkTimer += 0.1;
        if (walkTimer > 2)
            walkTimer = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            position.add(2, 0);
            if (IsCollidingWithEntityType("Block"))
                position.add(-2, 0);
            if (walkTimer <= 1)
                SetSprite("player_right_1.png");
            else
                SetSprite("player_right_0.png");
            walkDir = "right";

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            position.add(-2, 0);
            if (IsCollidingWithEntityType("Block"))
                position.add(2, 0);
            if (walkTimer <= 1)
                SetSprite("player_left_1.png");
            else
                SetSprite("player_left_0.png");
            walkDir = "left";
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            position.add(0, 2);
            if (IsCollidingWithEntityType("Block"))
                position.add(0, -2);
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
            position.add(0, -2);
            if (IsCollidingWithEntityType("Block"))
                position.add(0, 2);
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
        
        super.Update();
    }
}
