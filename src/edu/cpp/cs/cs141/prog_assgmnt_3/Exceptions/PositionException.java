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
package edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions;

import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * @author Nick Huiting
 * Exception for an invalid position.
 */
public class PositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private final Position position;
	
	/**
	 * Creates a new exception with a position.
	 * @param message
	 * @param position
	 */
	public PositionException(String message, Position position) {
		super(message);
		this.position = position;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String message = super.toString(); 
		return  message + "\n\nPosition: " + position;
	}
}
