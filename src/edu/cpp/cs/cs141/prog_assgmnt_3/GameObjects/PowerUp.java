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

public class PowerUp extends GameObject {
	private PowerUpType type;
	
	public PowerUpType getType() {
		return type;
	}
	
	@Override
	public char getSymbol() {
		//todo different symbol for each power up type
		switch (type) {
			case Radar:
				break;
			case Invincibility:
				break;
			case Ammo:
				break;
			default:
				break;
		}
		
		return 0;
	}
	
	public void randomPosition() {
		//todo 
	}
}
