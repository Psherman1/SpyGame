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

import java.io.Serializable;

/**
 * Command sent from the engine to the UI to perform a UI-specific action including displaying game information, errors, and special messages.
 */
public enum UICommand implements Serializable {
	/**
	 * Print a help screen
	 */
	PrintHelp,
	
	/**
	 * Print the game result.
	 */
	PrintGame,
	
	/**
	 * Print a look prompt.
	 */
	PrintLook,
	
	/**
	 * Print an error specifying the player already looked this turn.
	 */
	PrintAlreadyLooked,
	
	/**
	 * Print an IO error.
	 */
	PrintIOError,
	
	/**
	 * Print that there was an error moving this turn.
	 */
	PrintMoveError,
	
	/**
	 * Print that the given input was unable to be processed.
	 */
	PrintInputError,
	
	/**
	 * Print that the shot hit a ninja.
	 */
	PrintShootHit,
	
	/**
	 * Print that the shot missed a ninja.
	 */
	PrintShootMiss,
	
	/**
	 * Print that the user cannot shoot as they have no ammo left.
	 */
	PrintNoAmmo,
	
	/**
	 * Print that the player died this turn.
	 */
	PrintDead,
	
	/**
	 * Do not print anything after the turn.
	 */
	None, 
	
	/**
	 * Print that the game has ended.
	 */
	PrintEnd
}
