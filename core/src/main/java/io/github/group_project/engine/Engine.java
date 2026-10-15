package io.github.group_project.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.group_project.engine.entity.Entity;

//The Engine class contains everything that will need to be saved globally
public class Engine {
    public static List<Entity> entities = new ArrayList<Entity>(); //All active entities
    public static SpriteBatch spriteBatch = new SpriteBatch(); //Rendering thing

    public Engine()
    {
    }

    public static <T> Entity spawnEntity(Class<T> entity, int x, int y) //Creates an entity and adds them to the list of active entities
    {
        try {
            Entity newEntity = (Entity)entity.getDeclaredConstructor().newInstance(); //Magic
            newEntity.Setup(x, y, entities.size()); //The new entity's ID is the current number of entities in the game, so it starts at 0 and counts up
            entities.add(newEntity);
            return newEntity;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No parameterless constructor found for " + entity.getName(), e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate " + entity.getName(), e);
        }
    }

    public static void destroyEntity(Entity entity) //Removes the entity from the entity list, taking them out of scope. i think.
    {
        for (int i = entities.size() - 1; i > entity.id; i--)
        {
            entities.get(i).id--; //Every entity with an id greater than the one this one is lowered, so that there isnt an unused ID
        }
        entities.remove(entity.id); //Blow up the entity by using their id to find their spot in the list
    }
}
