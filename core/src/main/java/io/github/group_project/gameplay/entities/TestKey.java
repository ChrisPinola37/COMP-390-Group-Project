package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class TestKey extends Entity {

    public TestKey()
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
        


            Engine.spawnEntity(TestGoalOpen.class, 110, 80);
            destroyed = true;
            
        }
        super.Update();
    }
}
