package io.github.group_project.gameplay.entities;

import java.util.Vector;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeTrapTile extends Entity {

    public MazeTrapTile()
    {
        super();
        name = "Trap Tile";
        SetSprite("trap_tile_off.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
    }

    float activeTimer;
    int active = 0;

    @Override 
    public void Update()
    {
        delta = Gdx.graphics.getDeltaTime();
        activeTimer += delta;
        if (activeTimer >= 1.5) {
            if(active == 0) {
                SetSprite("trap_tile_on.png");
                active = 1;
            }
            else {
                SetSprite("trap_tile_off.png");
                active = 0;
            }
            activeTimer = 0;
        }

        if (IsCollidingWithEntityType(hitbox,"Player") && active == 1)
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

        super.Update();
    }
}
