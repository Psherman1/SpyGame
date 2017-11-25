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
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

import java.io.Serializable;

import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * @author JoseRodriguez
 *  Super class created for the Enemy class and Player class.
 */
public abstract class ActiveAgent extends GameObject implements Serializable {
	
	/**
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
	 * Gets the priority with which to render the object.  Higher priorities are rendered on top of lower priorities.
	 * @return Enum of VisibilityPriority; None, Elevated, AlwaysRender.
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.Elevated;
	}
}
