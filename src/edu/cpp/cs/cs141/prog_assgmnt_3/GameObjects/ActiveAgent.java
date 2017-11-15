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
 * Base class created for the Enemy class and Player class.
 */
public abstract class ActiveAgent extends GameObject {
	
	/**
	 * 
	 * @param pos
	 */
	protected ActiveAgent(Position pos) {
		super(pos);
	}
	
	/** 
	 * Method signature for an Enemy object killing a Player object or
	 * a Player object killing an Enemy object.
	 */
	public abstract void kill();
	
	/**
	 * Moves the agent to a new position.
	 * @param position
	 */
	public void move(Position position) {
		this.position = position;
	}
	
	/*
	 * 
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.Elevated;
	}
}
