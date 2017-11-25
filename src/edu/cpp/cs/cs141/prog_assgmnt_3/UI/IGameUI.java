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
package edu.cpp.cs.cs141.prog_assgmnt_3.UI;

import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameState;
import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameTurnResult;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;

/**
 *An interface for all user interfaces to interact with the game.
 */
public interface IGameUI {
	/**
	 * Get an input string value to process commands.
	 * @return Returns an upper case string. 
	 */
	String getKeyInput(GameState state) throws GameStateException;
	
	/**
	 * Displays a result from a game turn.
	 */
	void updateUI(GameTurnResult result);
	
	/**
	 * General purpose initialization method.  This should be called only once.
	 */
	void initialize();
}
