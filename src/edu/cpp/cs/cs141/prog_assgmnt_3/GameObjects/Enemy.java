/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment FinalProject
 *
 * A simple yet interesting text-based game where a user is trapped in a square room and must locate 
 * a briefcase hidden in a room, all while avoiding ninja assassins and using power ups to survive.
 * The game is turn based, and allows for loading and saving progress.
 *
 *	 Team Members (Broncodes):
 *   Nick Huiting
 *   Jose Rodriguez
 *   Thanh Doan
 *   Tenzin Tashitsang
 *   Dennis Jimenez
 *   Michael Ackerman
 */
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

import java.io.Serializable;

import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * @author JoseRodriguez
 *	This class represents the Enemy/Ninja.
 */
public class Enemy extends ActiveAgent implements Serializable {

	/**
	 * @param pos from the Position class. Sets the X and Y coordinates of the Enemy
	 * position on the grid.
	 */
	public Enemy(Position pos) {
		super(pos);
	}

	/**
	 * Setting the symbol for the enemy/ninja.
	 * @param debug Used to allow the enemies to be seen for the debug mode.
	 * @param radar Sets the visual distance of the enemy.
	 * @return the symbol of the Enemy. Used to Symbolically represent the 
	 * enemy/ninja on the grid.
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return 'X';
	}
}
