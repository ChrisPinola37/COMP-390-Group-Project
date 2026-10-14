package io.github.group_project.engine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import io.github.group_project.engine.Engine;

//Parent class for every entity in the game
public class Entity {
    public String name = ""; //String used to identify a subclass

    public Texture sprite; //Its current sprite

    public Vector2 position = new Vector2(); //A vector containing the entity's x and y position

    public Entity()
    {
       SetSprite("libgdx.png");
    }
    public void Update() //Runs every frame
    {
        IsCollidingWithEntityType("Test Entity");
    }

    public void Draw() //Draw entity's sprite to screen
    {
        Engine.spriteBatch.draw(sprite, position.x, position.y);
    }

    public void SetSprite(String spriteName) //Changes the sprite based on the string provided
    {
        sprite = new Texture(spriteName); //I don't think this is correct but it works.
    }

    public Boolean IsCollidingWithEntityType(String entityName)
    {
        for (int i = 0; i < Engine.entities.size(); i++)
        {
        }
        
        return true;
    }
}
