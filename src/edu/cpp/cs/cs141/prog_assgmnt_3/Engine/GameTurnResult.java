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

import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UICommand;

/**
 * A result returned from the game for the UI to display.
 */
public class GameTurnResult {
	private String[] gridLines;
	private int lives;
	private UICommand command;
	PlayerStatus status;
	
	/**
	 * Creates a barebones result with a UICommand.  This should be used if the game is not going to be printed this turn.
	 * @param command
	 */
	public GameTurnResult(UICommand command) {
		this.gridLines = new String[0];
		this.lives = 0;
		this.command = command;
	}
	
	/**
	 * Creates a result with all the necessary game information the user needs.
	 * @param gridLines
	 * @param lives
	 * @param command
	 * @param status
	 */
	public GameTurnResult(String[] gridLines, int lives, UICommand command, PlayerStatus status) {
		this.gridLines = gridLines;
		this.lives = lives;
		this.command = command;
		this.status = status;
	}
	
	/**
	 * The player's status this turn.
	 * @return
	 */
	public PlayerStatus getStatus() {
		return status;
	}
	
	/**
	 * The command to be processed by the UI when it comes time to visually represent the turn result. 
	 * @return
	 */
	public UICommand getCommand() {
		return command;
	}
	
	/**
	 * Array of strings each representing a row in the game grid. 
	 * @return
	 */
	public String[] getGridLines() {
		return gridLines;
	}
	
	/**
	 * How many lives the player has remaining.
	 * @return
	 */
	public int getLives() {
		return lives;
	}
}
