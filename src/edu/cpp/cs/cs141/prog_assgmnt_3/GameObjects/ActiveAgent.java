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
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

/**
 * @author JoseRodriguez
 * Base class created for the Enemy class and Player class.
 */
public abstract class ActiveAgent extends GameObject {
	/** 
	 * Method signature for an Enemy object killing a Player object or
	 * a Player object killing an Enemy object.
	 */
	public abstract void kill();
	
	/**
	 * Method signature for allowing the view distance of the Player and Enemy to change.
	 */
	public abstract void look();
	
	@Override
	public int getPriority() {
		return 1;
	}
}
