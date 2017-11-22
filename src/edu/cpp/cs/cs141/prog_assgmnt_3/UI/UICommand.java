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

import java.io.Serializable;

/**
 * Command sent from the engine to the UI to perform a UI-specific action in lieu of displaying game information.
 */
public enum UICommand implements Serializable {
	PrintHelp,
	PrintGame,
	PrintLook,
	PrintAlreadyLooked,
	PrintMoveError,
	PrintInputError,
	PrintShootHit,
	PrintShootMiss,
	PrintNoAmmo,
	PrintDead,
	None, 
	PrintEnd
}
