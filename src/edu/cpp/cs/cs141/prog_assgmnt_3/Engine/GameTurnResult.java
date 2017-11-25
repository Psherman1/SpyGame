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
	
	public GameTurnResult(UICommand command) {
		this.gridLines = new String[0];
		this.lives = 0;
		this.command = command;
	}
	
	public GameTurnResult(String[] gridLines, int lives, UICommand command, PlayerStatus status) {
		this.gridLines = gridLines;
		this.lives = lives;
		this.command = command;
		this.status = status;
	}
	
	public PlayerStatus getStatus() {
		return status;
	}
	
	public UICommand getCommand() {
		return command;
	}
	
	public String[] getGridLines() {
		return gridLines;
	}
	
	public int getLives() {
		return lives;
	}
}
