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
 * @author TenzinTashitsang
 * A class for initializing the types of power ups, setting their positions, assigning their symbols.
 */
public class PowerUp extends GameObject {
	
	/**
	 * Default constructor of PowerUp.
	 * @param pos used to assign a position to the powerUp on the grid.
	 * @param type of the poweUp that is being used.
	 */
	public PowerUp(Position pos, PowerUpType type) {
		super(pos);
		this.type = type;
	}
	
	/**
	 * The type of power up; invincibility, ammo, radar.
	 */
	private PowerUpType type;
	
	/**
	 * Gets the type of power up.
	 * @return type of the power up.
	 */
	public PowerUpType getType() {
		return type;
	}
	
	/**
	 * Gets the symbol of the powerUp depending on what is picked up.
	 * @param debug used for allowing the item to be seen in debug mode.
	 * @param radar shows the power up if the player is close enough to see.
	 * @return The type of power up; 'R','I','A'.
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
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
