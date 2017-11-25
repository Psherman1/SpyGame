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
package edu.cpp.cs.cs141.prog_assgmnt_3.Engine;


public class PlayerStatus {
	private boolean radar;
	private boolean invincibility;
	private boolean hasAmmo; 
	private int invincibleTurns;
	
	public PlayerStatus(boolean radar, boolean invincibility, boolean hasAmmo, int invincibleTurns) {
		this.radar = radar;
		this.invincibility = invincibility;
		this.hasAmmo = hasAmmo;
		this.invincibleTurns = invincibleTurns;
	}
	
	public int getInvincibleTurns() {
		return invincibleTurns;
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
