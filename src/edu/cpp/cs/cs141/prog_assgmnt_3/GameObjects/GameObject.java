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
 *  @author Nick Huiting
 *  Super class used to represent a basic game object that can be added to a 2D grid and represented by a character symbol.
 */
public abstract class GameObject implements Serializable {
	/**
	 * Holds the objects position in 2D space.
	 */
	protected Position position;
	
	/**
	 * Creates a new object from a position.
	 * @param pos The position on the grid.
	 */
	protected GameObject(Position pos) {
		position = pos;
	}

	/**
	 * Gets the object's position in 2D space.
	 * @return position of the object.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Gets a visual representation of the object.
	 * @return the char symbol of the object.  This is used to represent objects on a text-based grid.
	 */
	public abstract char getSymbol(boolean debug, boolean radar);
	
	/**
	 * @return Always returns None by default.  Should be overridden.
	 */
	public VisibilityPriority getPriority() {
		return VisibilityPriority.None;
	}
}
