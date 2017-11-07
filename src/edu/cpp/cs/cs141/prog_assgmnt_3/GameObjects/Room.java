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

public class Room extends GameObject {

	public Room(Position pos) {
		super(pos);
	}

	@Override
	public char getSymbol() {
		return '@'; //TODO change this
	}
	
	public boolean hasBriefcase() {
		// TODO: Implement function
		return true;
	}
	
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.AlwaysRender;
	}
}
