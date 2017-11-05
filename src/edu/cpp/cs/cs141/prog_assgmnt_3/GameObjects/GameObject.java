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

import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * 
 */
public abstract class GameObject {
	private Position position;
	
	/**
	 * 
	 * @return
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * 
	 * @return
	 */
	public abstract char getSymbol();
	
	/**
	 * 
	 * @return
	 */
	public int getPriority() {
		return 0;
	}
}
