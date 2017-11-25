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
 * @author Dennis Jimenez
 * A class representing the rooms on the grid.
 * Contains the position, if it contains a briefcase, and the symbol for the room.
 */
public class Room extends GameObject implements Serializable {
	/**
	 * Used to determine whether a room has a briefcase.  False if there is no briefcase and true if there is a briefcase.
	 */
	boolean hasBriefcase;
	
	/**
	 * Creates a new room with a position.  This room will not have a briefcase.
	 * Allows the room to be positioned on the grid using the position class.
	 * @param pos from the Position class, as X and Y coordinates.
	 */
	public Room(Position pos) {
		super(pos);
	}
	
	/**
	 * Creates a room with a position and optional briefcase.
	 * Allows the room to be positioned on the grid using the position class and 
	 * passes the argument for giving the room a briefcase.
	 * @param pos from the Position class, as X and Y coordinates.
	 * @param briefcase boolean value set to true only when the briefcase is in the room.
	 */
	public Room(Position pos, boolean briefcase) {
		super(pos);
		hasBriefcase = false;
	}

	/**
	 * Gets the symbol on the grid for the rooms.
	 * @param debug used to show the room when the debug mode is activated.
	 * @param radar allows the room to be seen depending on the distance of the player.
	 * @return the default char '@' unless debug is activated which would return '@' unless the
	 * room has the briefcase, in which case it will return '!'. If the radar is active and the room has a briefcase, the briefcase symbol will be returned..
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return (debug || radar) && hasBriefcase  ? '!': '@';
	}
	
	/**
	 * Setter method that will set the hasBriefcase to true.
	 */
	public void setHasBriefcaseTrue() {
		hasBriefcase = true;
	}
	
	/**
	 * Getter method that will show whether the room has a briefcase or not.
	 * @return the boolean value of hasBriefcase depending on if the room contains the case or not.
	 */
	public boolean hasBriefcase() {
		return hasBriefcase;
	}
	
	/**
	 * Getter method that returns the visibility priority.
	 * @return the visibility of the rooms set to always render.
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.AlwaysRender;
	}
}
