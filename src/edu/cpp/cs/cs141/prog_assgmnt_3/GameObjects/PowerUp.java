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

public class PowerUp extends GameObject {
	
	public PowerUp(Position pos, PowerUpType type) {
		super(pos);
		this.type = type;
	}

	private PowerUpType type;
	
	public PowerUpType getType() {
		return type;
	}
	
	@Override
	public char getSymbol() {
		switch (type) {
			case Radar:
				return 'R';
			case Invincibility:
				return 'I';
			case Ammo:
				return 'A';
			default:
				return 0;
		}
	}
}
