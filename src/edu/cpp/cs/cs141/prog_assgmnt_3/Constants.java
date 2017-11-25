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
package edu.cpp.cs.cs141.prog_assgmnt_3;

/**
 * Container for all game constants to be used by the engine and elsewhere.
 */
public final class Constants {
	/**
	 * Specifies the number of lives the player has.
	 */
	public static final int PlayerLives = 3;
	
	/**
	 * Vertical size of the grid.
	 */
	public static final int GridRows = 9; //y
	
	/**
	 * Horizontal size of the grid.
	 */
	public static final int GridColumns = 9; //x
	
	/**
	 * How far a player can look
	 */
	public static final int LookDistance = 2;
	
	/**
	 * The the number of enemies that will spawn.
	 */
	public static final int EnemyCount = 6;
	
	/**
	 * The number of rooms in the grid.
	 */
	public static final int RoomCount = 9;
	
	/**
	 * The the number of turns a player will be invincible once he/she gets the Invincibility PowerUp.
	 */
	public static final int InvincibleTurns = 5;
	
	/**
	 * the minimum distance between the player and an enemy ninja at the start of the game.  That way you don't start a game right next to a ninja.
	 */
	public static final int StartingPositionTolerance = 3;
}
