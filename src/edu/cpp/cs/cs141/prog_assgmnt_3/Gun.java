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
package edu.cpp.cs.cs141.prog_assgmnt_3;

import java.io.Serializable;

/**
 * Simple Gun class that manages the ammo.
 */
public class Gun implements Serializable {
	private int ammo = 1;

	/**
	 * Method to check if the gun has ammo.
	 * @return true if there is ammo, false otherwise.
	 */
	public boolean hasAmmo() {
		return ammo > 0;
	}

	/**
	 * Setter Method to change the amount of ammo in the gun. Used to implement the effect of the ammo PowerUp.
	 * @param ammo integer amount to set the ammo to.
	 */
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
}
