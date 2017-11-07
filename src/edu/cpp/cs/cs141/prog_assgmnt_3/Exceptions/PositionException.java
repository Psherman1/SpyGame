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
package edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions;

import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 *
 */
public class PositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Position position;
	
	public PositionException(String message, Position position) {
		super(message);
		this.position = position;
	}
	
	@Override
	public String toString() {
		String message = super.toString(); 
		return  message + "\n\nState: " + position;
	}
}
