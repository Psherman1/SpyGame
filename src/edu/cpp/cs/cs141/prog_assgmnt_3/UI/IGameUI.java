/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment FinalProject
 *
 * <description-of-assignment>
 *
 *	 Team Members:
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
	 * 
	 * Later to be overridden as a result returned from the game for the UI to display.
	 */
	void updateUI(GameTurnResult result);
	
	/**
	 * 
	 * Later to be overridden as welcome message prompt for user input.
	 */
	void initialize();
}
