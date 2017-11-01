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

/**
 * @author JoseRodriguez
 *	This class represents the Enemy. It implements the ActiveAgent interface.
 */
public class Enemy extends ActiveAgent {

	@Override
	public void kill() {
		// TODO make it so Enemy can kill the player
		
	}

	@Override
	public void look() {
		// TODO make it so the view distance can be changed depending on difficulty
		
	}

	@Override
	public char getSymbol() {
		return 'X';
	}

}
