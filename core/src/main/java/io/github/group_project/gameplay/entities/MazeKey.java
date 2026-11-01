package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.entities.MazeGoal;

public class MazeKey extends Entity {

    public MazeKey()
    {
        super();
        name = "Key";
        SetSprite("basic_key.png");
    }

    @Override 
    public void Update()
    {
        if (IsCollidingWithEntityType("Player"))
        {
            Sound pickup = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
            pickup.play(0.1f);
        

            int goalID = Engine.getFirstEntityIDOfType("Goal");
            if (goalID > -1)
            {
                MazeGoal goalEntity = (MazeGoal) Engine.entities.get(goalID);
                goalEntity.Open();
            }
            destroyed = true;
            
        }
        super.Update();
    }
}
