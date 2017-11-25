/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment FinalProject
 *
 * A simple yet interesting text-based game where a user is trapped in a square room and must locate 
 * a briefcase hidden in a room, all while avoiding ninja assassins and using power ups to survive.
 * The game is turn based, and allows for loading and saving progress.
 *
 *	 Team Members (Broncodes):
 *   Nick Huiting
 *   Jose Rodriguez
 *   Thanh Doan
 *   Tenzin Tashitsang
 *   Dennis Jimenez
 *   Michael Ackerman
 */
package edu.cpp.cs.cs141.prog_assgmnt_3.Engine;

import edu.cpp.cs.cs141.prog_assgmnt_3.CardinalDirection;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Room;
import edu.cpp.cs.cs141.prog_assgmnt_3.Grid;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UICommand;

import java.io.*;
import java.io.Serializable;

/**
 * @author Nick Huiting
 *	A serializable class to save parts of the Game engine that can then be reloaded at a later time.
 */
public class GameSave implements Serializable {
    private boolean debug;
    private int lives;
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Room[] rooms;
    private CardinalDirection lookDirection;
    private int invincibleTurns;

    /**
     * Whether the game was in debug mode.
     * @param debug
     */
    public void setDebug(boolean debug) {
    	this.debug = debug;
    }
    
    /**
     * How many lives the player has.
     * @param lives
     */
    public void setLives(int lives) {
    	this.lives = lives;
    }
    
    /**
     * The grid being played in.
     * @param grid
     */
    public void setGrid(Grid grid) {
    	this.grid = grid;
    }
    
    /**
     * All current enemies.
     * @param enemies
     */
    public void setEnemies(Enemy[] enemies) {
    	this.enemies = enemies;
    }
    
    /**
     * All the rooms in the game.
     * @param rooms
     */
    public void setRoom(Room[] rooms) {
    	this.rooms = rooms;
    }
    
    /**
     * The direction the player is looking.
     * @param lookDirection
     */
    public void setLookDirection(CardinalDirection lookDirection) {
    	this.lookDirection = lookDirection;
    }
    
    /**
     * The player in the game.
     * @param player
     */
    public void setPlayer(Player player) {
    	this.player = player;
    }
    
    /**
     * How many turns the player is still invincible for.
     * @param invincibleTurns
     */
    public void setInvincibleTurns(int invincibleTurns) {
    	this.invincibleTurns = invincibleTurns;
    }
    
    /**
     * Whether the game was in debug mode.
     * @return
     */
    public boolean getDebug() {
    	return debug;
    }
    
    /**
     * How many lives the player has.
     * @return
     */
    public int getLives() {
    	return lives;
    }
    
    /**
     * The grid being played in.
     * @return
     */
    public Grid getGrid() {
    	return grid;
    }
    
    /**
     * All current enemies.
     * @return
     */
    public Enemy[] getEnemies() {
    	return enemies;
    }
    
    /**
     * All the rooms in the game.
     * @return
     */
    public Room[] getRoom() {
    	return rooms;
    }

    /**
     * The direction the player is looking.
     * @return
     */
    public CardinalDirection getLookDirection() {
    	return lookDirection;
    }
    
    /**
     * The player in the game.
     * @return
     */
    public Player getPlayer() {
    	return player;
    }
    
    /**
     * How many turns the player is still invincible for.
     * @return
     */
    public int getInvincibleTurns() {
    	return invincibleTurns;
    }
    
    /**
     * Saves a game to a file with the given relative filename.
     * Throws an IO exception if an error is encountered.
     */
    public static void saveGameToFile(String filename, GameSave gameSaveObj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(gameSaveObj);
        out.close();
        fileOut.close();
    }

    /**
     * Loads a saved game from a file given a relative filename..
     * @param filename name of path to file.
     * @return Returns a GameSave Object to be later loaded into GameEngine.
     */
    public static GameSave loadGameSaveFromFile(String filename) throws IOException, ClassNotFoundException {
        GameSave gameSaveObj = null;
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        gameSaveObj = (GameSave) in.readObject();
        in.close();
        fileIn.close();
        return gameSaveObj;
    }
}
