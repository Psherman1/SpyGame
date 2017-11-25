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
 *  Super class created for the Enemy class and Player class.  These objects have the ability to move.
 */
public abstract class ActiveAgent extends GameObject implements Serializable {
	
	/**
	 * Creates a new agent with a starting position.
	 * @param pos The position in the grid.
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
	 * @return Elevated priority.  Agents are rendered on top of other objects such as power ups.
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.Elevated;
	}
}
