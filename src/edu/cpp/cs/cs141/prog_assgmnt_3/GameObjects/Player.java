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

import edu.cpp.cs.cs141.prog_assgmnt_3.Gun;

/**
 * @author JoseRodriguez
 * This class represent the player. It implements the ActiveAgent interface.
 */
public class Player extends ActiveAgent {
	private Gun gun;
	
	public boolean canAttack() {
		return gun.hasAmmo();
	}

	@Override
	public void kill() {
		// TODO make it so the Player can kill the Enemy.
		
	}

	@Override
	public void look() {
		// TODO add code to allow the player to change view distances...
		
	}

	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 'P';
	}
}
