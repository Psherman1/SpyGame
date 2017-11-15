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
	boolean hasBriefcase;
	
	public Room(Position pos) {
		super(pos);
	}
	
	public Room(Position pos, boolean briefcase) {
		super(pos);
		hasBriefcase = false;
	}

	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return (debug || radar) && hasBriefcase  ? '!': '@';
	}
	
	public void setHasBriefcaseTrue() {
		hasBriefcase = true;
	}
	public boolean hasBriefcase() {
		return hasBriefcase;
	}
	
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.AlwaysRender;
	}
}
