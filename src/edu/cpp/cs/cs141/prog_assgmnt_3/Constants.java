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
package edu.cpp.cs.cs141.prog_assgmnt_3;

/**
 * Container for all game constants to be used by the engine and elsewhere. Player lives specifies the number of
 * lives the player has. GridRows and GridColumns refers to the size of the grid. LookDistance is how far a player
 * can look. EnemyCount refers the the number of enemies that will spawn. InvincibleTurns refers the the number of
 * turns a player will be invincible once he/she gets the Invincibility PowerUp. StartingPositionTolerance refers
 * to the minimum distance between the player and an enemy ninja at the start of the game (That way you don't start
 * a game right next to a ninja).
 */
public final class Constants {
	public static final int PlayerLives = 3;
	
	public static final int GridRows = 9; //y
	public static final int GridColumns = 9; //x
	
	public static final int LookDistance = 2;
	public static final int EnemyCount = 6;
	public static final int RoomCount = 9;
	public static final int InvincibleTurns = 5;
	public static final int StartingPositionTolerance = 3;
}
