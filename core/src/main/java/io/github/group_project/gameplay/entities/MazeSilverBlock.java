package io.github.group_project.gameplay.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;
import io.github.group_project.engine.entity.Entity;

public class MazeSilverBlock extends Entity {

    public MazeSilverBlock()
    {
        super();
        name = "Silver Block";
        SetSprite("silver_block.png");
        hitbox.setSize(sprite.getWidth(),sprite.getHeight());
    }
}
