package io.github.group_project.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;
import io.github.group_project.gameplay.AudioManager;

public class Victory extends Entity {

    public Victory()
    {
        super();
        name = "Victory Demo";
        SetSprite("win_demo.png");
        AudioManager.instance.stopMusic();
        AudioManager.instance.playSound("win", 0.085f);
        
    }
    

    @Override 
    public void Update()
    {
        super.Update();
    }
}
