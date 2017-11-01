/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
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
 * @author JoseRodriguez
 * Interface created for the Enemy class and Player class.
 */
public interface ActiveAgent {
	/** 
	 * Method signature for an Enemy object killing a Player object or
	 * a Player object killing an Enemy object.
	 */
	void kill();
	
	/**
	 * Method signature for allowing the view distance of the Player and Enemy to change.
	 */
	void look();
}
