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

/**
 *	This class represents the Enemy/Ninja.
 */
public class Enemy extends ActiveAgent {

	/**
	 * 
	 * @param pos
	 */
	public Enemy(Position pos) {
		super(pos);
	}

	/**
	 * 
	 * @param debug
	 * @param radar
	 * @return
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return 'X';
	}
}
