/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #N
 *
 * <description-of-assignment>
 *
 *   Nick Huiting
 */
package edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions;

import edu.cpp.cs.cs141.prog_assgmnt_3.GameState;

/**
 * @author Nick Huiting
 *
 */
public class GameStateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameState state;
	public GameStateException(String message, GameState state) {
		super(message);
		this.state = state;
	}
	
	@Override
	public String getMessage() {
		String message = super.getMessage(); 
		return  message + "\n\nState: " + state;
	}
}
