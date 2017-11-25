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
 * Sets the position, if it contains a briefcase, the symbol for the room, and its visibility.
 */
public class Room extends GameObject implements Serializable {
	/**
	 * hasBriefcase is used to give the room a false if there is no briefcase and true if there is a briefcase.
	 */
	boolean hasBriefcase;
	
	/**
	 * Default constructor
	 * Allows the room to be positioned on the grid using the position class.
	 * @param pos from the Position class, as X and Y coordinates.
	 */
	public Room(Position pos) {
		super(pos);
	}
	
	/**
	 * Default constructor
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
	 * Gives the symbol on the grid for the rooms.
	 * @param debug used to show the room when the debug mode is activated.
	 * @param radar allows the room to be seen depending on the distance of the player.
	 * @return the default char '@' unless debug is activated which would return '@' unless the
	 * room has the briefcase it will return 'B' and if the radar is within reach to show if the
	 * briefcase is in the room.
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return (debug || radar) && hasBriefcase  ? '!': '@';
	}
	
	/**
	 * Setter method that will set the hasBriefcase to true if the room contains the briefcase.
	 */
	public void setHasBriefcaseTrue() {
		hasBriefcase = true;
	}
	
	/**
	 * Getter method that will show wether the room has a briefcase or not.
	 * @return the boolean value of hasBriefcase depending on if the room containts the case or not.
	 */
	public boolean hasBriefcase() {
		return hasBriefcase;
	}
	
	/**
	 * Getter method that renders the visibility.
	 * @return the visibility of the rooms set to always render.
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.AlwaysRender;
	}
}
