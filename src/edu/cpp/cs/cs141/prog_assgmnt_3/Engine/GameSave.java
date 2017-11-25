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
 * 
 * @author Nick Huiting
 *
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

    public void setDebug(boolean debug) {
    	this.debug = debug;
    }
    
    public void setLives(int lives) {
    	this.lives = lives;
    }
    
    public void setGrid(Grid grid) {
    	this.grid = grid;
    }
    
    public void setEnemies(Enemy[] enemies) {
    	this.enemies = enemies;
    }
    
    public void setRoom(Room[] rooms) {
    	this.rooms = rooms;
    }
    
    public void setCardinalDirection(CardinalDirection lookDirection) {
    	this.lookDirection = lookDirection;
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    }
    
    public void setInvincibleTurns(int invincibleTurns) {
    	this.invincibleTurns = invincibleTurns;
    }
    
    public boolean getDebug() {
    	return debug;
    }
    
    public int getLives() {
    	return lives;
    }
    
    public Grid getGrid() {
    	return grid;
    }
    
    public Enemy[] getEnemies() {
    	return enemies;
    }
    
    public Room[] getRoom() {
    	return rooms;
    }

    public CardinalDirection getCardinalDirection() {
    	return lookDirection;
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public int getInvincibleTurns() {
    	return invincibleTurns;
    }
    
    
    /**
     * Caller of this Function needs to handle io exceptions!
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
