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
package edu.cpp.cs.cs141.prog_assgmnt_3.Engine;


public class PlayerStatus {
	private boolean radar;
	private boolean invincibility;
	private boolean hasAmmo; 
	
	public PlayerStatus(boolean radar, boolean invincibility, boolean hasAmmo) {
		this.radar = radar;
		this.invincibility = invincibility;
		this.hasAmmo = hasAmmo;
	}
	
	public boolean hasAmmo() {
		return hasAmmo;
	}
	
	public boolean hasRadar() {
		return radar;
	}
	
	public boolean isInvincible() {
		return invincibility;
	}
}
