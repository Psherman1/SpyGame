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
    protected boolean debug;
    protected int lives;
    protected Grid grid;
    protected GameState state;
    protected Player player;
    protected Enemy[] enemies;
    protected Room[] rooms;
    protected UICommand command;
    protected CardinalDirection lookDirection;
    protected int invincibleTurns;

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
