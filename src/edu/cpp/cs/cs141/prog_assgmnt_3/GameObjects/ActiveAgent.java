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
 * @author JoseRodriguez
 *  Super class created for the Enemy class and Player class.
 */
public abstract class ActiveAgent extends GameObject {
	
	/**
	 * 
	 * @param pos from Position class gives both the X and Y coordinates
	 */
	protected ActiveAgent(Position pos) {
		super(pos);
	}
	
	/**
	 * Moves the agent to a new position.
	 * @param position in both the X and Y coordinates.
	 */
	public void move(Position position) {
		this.position = position;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.Elevated;
	}
}
