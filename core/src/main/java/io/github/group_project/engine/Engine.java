package io.github.group_project.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.group_project.engine.entity.Entity;

//The Engine class containts everything that will need to be saved globally
public class Engine {
    public static List<Entity> entities = new ArrayList<Entity>(); //All active entities
    public static SpriteBatch spriteBatch = new SpriteBatch(); //Rendering thing

    public Engine()
    {
    }

    public static <T> T spawnEntity(Class<T> entity, float x, float y) //Creates an entity and adds them to the list of active entities
    {
        try {
            T newEntity = entity.getDeclaredConstructor().newInstance();
            entities.add((Entity)newEntity);

            // Used to set entity position upon creation
            if (newEntity instanceof Entity) {
            Entity entityPos = (Entity) newEntity;
            entityPos.position.set(x, y);
    }
            
            return newEntity;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No parameterless constructor found for " + entity.getName(), e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate " + entity.getName(), e);
        }

    }

    public static void clean() //Cleans up and removes all entities that are marked as destroyed
    {
        entities.removeIf(entity -> entity.destroyed);
    }
}
