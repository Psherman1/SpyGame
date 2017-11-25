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
 *  @autho Nick Huiting
 *  Class used to hold all the positions and symbols of the ActiveAgents(Player/Enemies) and the power ups.
 *  Also to set their visibility levels.
 */
public abstract class GameObject implements Serializable {
	
	/**
	 * Field for holding the X and Y of a coordinate.
	 */
	protected Position position;
	
	/**
	 * Default Constructor.
	 * @param pos from the Position class. Sets the X and Y coordinates of the object.
	 * poisiton on the grid.
	 */
	protected GameObject(Position pos) {
		position = pos;
	}

	/**
	 * @return position of the object using the type from the Position class. Sets the X and Y coordinates
	 *  positon on the grid.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @return the char symbol of the object; '*','B','I','P','X','@','A' etc.
	 */
	public abstract char getSymbol(boolean debug, boolean radar);
	
	/**
	 * @return Enum of VisibilityPriority; None, Elevated, AlwaysRender.
	 */
	public VisibilityPriority getPriority() {
		return VisibilityPriority.None;
	}
}
