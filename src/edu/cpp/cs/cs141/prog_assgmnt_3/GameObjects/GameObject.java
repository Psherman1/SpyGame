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
	protected Position position;
	
	protected GameObject(Position pos) {
		position = pos;
	}

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
	public abstract char getSymbol(boolean debug);
	
	/**
	 * 
	 * @return
	 */
	public VisibilityPriority getPriority() {
		return VisibilityPriority.None;
	}
}
