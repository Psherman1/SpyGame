package edu.cpp.cs.cs141.prog_assgmnt_3.Engine;

import edu.cpp.cs.cs141.prog_assgmnt_3.CardinalDirection;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Room;
import edu.cpp.cs.cs141.prog_assgmnt_3.Grid;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UICommand;

import java.io.*;

public class GameSave implements Serializable {
    // protected so that GameEngine can access them without getters.
    // Note: we don't serialize the UI
    private boolean debug;
    private int lives;
    private Grid grid;
    private GameState state;
    private Player player;
    private Enemy[] enemies;
    private Room[] rooms;
    private UICommand command;
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
    
    public void setGameState(GameState state) {
    	this.state = state;
    }
    
    public void setEnemies(Enemy[] enemies) {
    	this.enemies = enemies;
    }
    
    public void setRoom(Room[] rooms) {
    	this.rooms = rooms;
    }
    
    public void setUICommand(UICommand command) {
    	this.command = command;
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
    
    public GameState getGameState() {
    	return state;
    }
    
    public Enemy[] getEnemies() {
    	return enemies;
    }
    
    public Room[] getRoom() {
    	return rooms;
    }
    
    public UICommand getUICommand() {
    	return command;
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
     *
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
