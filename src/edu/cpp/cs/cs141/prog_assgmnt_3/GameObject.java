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
package edu.cpp.cs.cs141.prog_assgmnt_3;

/**
 * @author Nick Huiting
 *
 */
public abstract class GameObject {
	private Position position;
	
	public Position getPosition() {
		return position;
	}
	
	
	public abstract char getSymbol();
}
