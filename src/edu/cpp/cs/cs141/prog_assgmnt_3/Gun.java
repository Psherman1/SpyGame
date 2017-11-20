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
package edu.cpp.cs.cs141.prog_assgmnt_3;


/**
 * Simple Gun class that manages the ammo.
 */
public class Gun {
	private int ammo = 1;

	/**
	 * Method to check if the gun as ammo.
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
