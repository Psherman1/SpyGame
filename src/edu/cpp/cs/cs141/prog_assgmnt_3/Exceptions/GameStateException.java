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

import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameState;

/**
 * @author Nick Huiting
 * Class to handle game state exceptions.
 */
public class GameStateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private GameState state;
	
	/**
	 * 
	 * @param message
	 * @param state
	 */
	public GameStateException(String message, GameState state) {
		super(message);
		this.state = state;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String message = super.toString(); 
		return  message + "\n\nState: " + state;
	}
}
