package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeBlackHole extends Entity {

    public MazeBlackHole()
    {
        super();
        name = "Black Hole";
        SetSprite("black_hole_0.png");
    }

    float animationTimer;
    float moveSpeed;


    @Override 
    public void Update()
    {
        if (hitbox == null) {
            hitbox = new Rectangle(position.x+8, position.y+8, 48, 48);
        } else {
            hitbox.setSize(48, 48);
            hitbox.setPosition(position.x+8, position.y+8);
        }

        if (Gdx.graphics.getDeltaTime() < 1) {
            delta = Gdx.graphics.getDeltaTime();
        }

        moveSpeed = 30 * delta;

        animationTimer += delta;

        if (animationTimer > 0.2)
            SetSprite("black_hole_1.png");
        if (animationTimer > 0.4) {
            SetSprite("black_hole_0.png");
            animationTimer = 0;
        }


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

        Vector2 dir = new Vector2(MazePlayer.position2.x - position.x, MazePlayer.position2.y - position.y);
        dir.nor();
        dir.scl(moveSpeed);
        position.set(position.x + dir.x, position.y + dir.y);

        super.Update();
    }
}
