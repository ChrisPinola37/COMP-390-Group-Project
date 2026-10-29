package io.github.group_project.gameplay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;

import com.badlogic.gdx.Gdx;

import io.github.group_project.engine.Engine;
import io.github.group_project.gameplay.entities.MazeGoal;
import io.github.group_project.gameplay.entities.MazeKey;
import io.github.group_project.gameplay.entities.MazePlayer;
import io.github.group_project.gameplay.entities.MazeWall;


public class MazeGenerator { //Handles the logic for generating a level's layout, along with saving and loading levels from a file
   static int screenWidth = Gdx.graphics.getWidth();
   static int screenHeight = Gdx.graphics.getHeight();

   static int gridSize = 64;

   static int rows = Math.round(screenHeight/gridSize);
   static int columns = Math.round(screenWidth/gridSize);

    static List<List<Cell>> allCells = new ArrayList<List<Cell>>();

    public static void generateMaze(String filePath)
    {
        allCells.clear();
        //Which cell the player starts on
        int startX = 0;
        int startY = Engine.random.nextInt(rows);

        int keyX = columns-1;
        int keyY = Engine.random.nextInt(rows);

        for (int y = 0; y < rows; y++) //Create a grid of cells
        {
            allCells.add(new ArrayList<Cell>());
            for (int x = 0; x < columns; x++)
            {
                allCells.get(y).add(new Cell(x, y));
            }
        }

        int finishedCells = 0;
        Deque<Cell> path = new ArrayDeque<Cell>();

        Cell currentCell = allCells.get(startY).get(startX);
        while (finishedCells < rows*columns) //This loop only ends once every cell has been reached
        {

            List<String> wallRemoveDirs = new ArrayList<String>();
            List<Cell> queryCells = new ArrayList<Cell>();
            if (currentCell.x < columns-1)
            {
                Cell potentialCell = allCells.get(currentCell.y).get(currentCell.x + 1);
                if (potentialCell.visited == false)
                {
                    queryCells.add(potentialCell);
                    wallRemoveDirs.add("Right");
                }
            }
            if (currentCell.x > 0)
            {
                Cell potentialCell = allCells.get(currentCell.y).get(currentCell.x - 1);
                if (potentialCell.visited == false)
                {
                    queryCells.add(potentialCell);
                    wallRemoveDirs.add("Left");
                }
            }
            if (currentCell.y < rows-1)
            {
                Cell potentialCell = allCells.get(currentCell.y+1).get(currentCell.x);
                if (potentialCell.visited == false)
                {
                    queryCells.add(potentialCell);
                    wallRemoveDirs.add("Down");
                }
            }
            if (currentCell.y > 0)
            {
                Cell potentialCell = allCells.get(currentCell.y - 1).get(currentCell.x);
                if (potentialCell.visited == false)
                {
                    queryCells.add(potentialCell);
                    wallRemoveDirs.add("Up");
                }
            }

            if (queryCells.size() == 0)
            {
                currentCell.visited = true;
                if (path.size() > 0)
                {
                    currentCell = path.pop();
                }
                else
                {
                    break;
                }
                continue;
            }
            path.push(currentCell);
            int nextCellIndex = Engine.random.nextInt(queryCells.size());
            Cell nextCell = queryCells.get(nextCellIndex);
            
            switch (wallRemoveDirs.get(nextCellIndex))
            {
                case "Right":
                    currentCell.walls.right = false;
                    nextCell.walls.left = false;
                break;
                case "Left":
                    currentCell.walls.left = false;
                    nextCell.walls.right = false;
                break;
                case "Down":
                    currentCell.walls.bottom = false;
                    nextCell.walls.top = false;
                break;
                case "Up":
                    currentCell.walls.top = false;
                    nextCell.walls.bottom = false;
                break;
            }

            currentCell.visited = true;
            currentCell = nextCell;
            finishedCells++;
        }
        //Logic is finished. Now create the list of walls

        List<Boolean> walls = new ArrayList<Boolean>();
        
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                Cell cell =  allCells.get(y).get(x);

                walls.add(cell.walls.left);
                walls.add(cell.walls.right);
                walls.add(cell.walls.top);
                walls.add(cell.walls.bottom);
                //A cell consists of 4 bits corresponding to the left, right, top and bottom wall
                //If the bit is 1, then there is a wall. If it's 0, there is no wall.
            }
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("levels/" + filePath))) {
            //Header
            dos.writeInt(startX); //Player Starting X
            dos.writeInt(startY); //Player Starting Y
            dos.writeInt(keyX); //Key X Coordinate
            dos.writeInt(keyY); //Key Y Coordinate
            dos.writeInt(walls.size()); //Amount of walls
            //End of Header

            //Save the walls
            for (int i = 0; i < walls.size(); i++)
            {
                dos.writeBoolean(walls.get(i)); 
            }
            System.out.println("Level saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadMaze(filePath);
    }

    public static void loadMaze(String filePath)
    {
        List<Boolean> walls = new ArrayList<Boolean>();
        int startX = 0;
        int startY = 0;
        int keyX = 0;
        int keyY = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream("levels/" + filePath)))
        {
            System.out.println("Level loaded successfully");
            //Read the header
            startX = dis.readInt();
            startY = dis.readInt();
            keyX = dis.readInt();
            keyY = dis.readInt();
            System.out.println("Start coordinates: " + startX + "," + startY);
            System.out.println("Key coordinates: " + keyX + "," + keyY);
            int wallAmnt = dis.readInt();

            //Loop reading the walls
            for (int i = 0; i < wallAmnt; i++)
            {
                walls.add(dis.readBoolean());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        loadMaze(walls, startX,startY,keyX,keyY);
    }

    public static void loadMaze(List<Boolean> walls,int startX, int startY, int keyX, int keyY)
    {
        for (int i = 0; i < walls.size(); i++)
        {
            int wallType = Math.floorMod(i, 4);

            boolean isWall = walls.get(i);

            int cellNumber = (int)Math.floor(i/4); //Every 4 bits = 1 cell in the maze

            int cellX = Math.floorMod(cellNumber, columns);
            int cellY = (int)Math.floor(cellNumber/columns);

            if (isWall == true)
            {
                switch (wallType)
                {
                    case 0:
                        Engine.spawnEntity(MazeWall.class, (cellX* gridSize), (cellY * gridSize)).setup(false);
                    break;
                    case 1:
                        Engine.spawnEntity(MazeWall.class, (cellX * gridSize) + 62, (cellY * gridSize)).setup(false);
                    break;
                    case 2:
                        Engine.spawnEntity(MazeWall.class, (cellX * gridSize), (cellY * gridSize)).setup(true);
                    break;
                    case 3:
                        Engine.spawnEntity(MazeWall.class, (cellX * gridSize), (cellY * gridSize) + 62).setup(true);
                    break;
                }
            }
        }
         //Spawn the player
        Engine.spawnEntity(MazePlayer.class, (startX*gridSize) + 16, (startY*gridSize));
        Engine.spawnEntity(MazeGoal.class, (startX*gridSize) + 16, (startY*gridSize) + 32);
        
        Engine.spawnEntity(MazeKey.class, keyX * gridSize, keyY * gridSize);
    }
}

class Exits
{
    Boolean top;
    Boolean bottom;
    Boolean left;
    Boolean right; 
    Exits(Boolean isClosed)
    {
        top = isClosed;
        bottom = isClosed;
        left = isClosed;
        right = isClosed;
    }
}

class Cell
{
    int x, y = 0;
    Exits walls = new Exits(true);
    Boolean visited = false;
    Boolean solution = false;

    Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}