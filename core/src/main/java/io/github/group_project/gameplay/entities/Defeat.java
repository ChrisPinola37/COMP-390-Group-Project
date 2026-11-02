package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.AudioManager;


public class Defeat extends Entity {

    public Defeat()
    {
        super();
        name = "Defeat Demo";
        SetSprite("lose_demo.png");
        AudioManager.instance.stopMusic();
        AudioManager.instance.playSound("lose", 0.1f);
    }

    @Override 
    public void Update()
    {
        super.Update();
    }
}
