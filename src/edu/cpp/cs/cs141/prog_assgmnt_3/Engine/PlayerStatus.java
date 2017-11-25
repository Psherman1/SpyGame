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

/**
 * @author Nick Huiting
 * Represents the status of the player to the UI for a given turn.  This class is immutable.
 */
public final class PlayerStatus {
	private final boolean radar;
	private final boolean invincibility;
	private final boolean hasAmmo; 
	private final int invincibleTurns;
	
	/**
	 * Creates a new status with player information.
	 * @param radar
	 * @param invincibility
	 * @param hasAmmo
	 * @param invincibleTurns
	 */
	public PlayerStatus(boolean radar, boolean invincibility, boolean hasAmmo, int invincibleTurns) {
		this.radar = radar;
		this.invincibility = invincibility;
		this.hasAmmo = hasAmmo;
		this.invincibleTurns = invincibleTurns;
	}
	
	/**
	 * How many turns remain where the player is invincible. 
	 * @return
	 */
	public int getInvincibleTurns() {
		return invincibleTurns;
	}
	
	/**
	 * Whether the player has ammo remaining.
	 * @return
	 */
	public boolean hasAmmo() {
		return hasAmmo;
	}
	
	/**
	 * Whether the player has radar active.
	 * @return
	 */
	public boolean hasRadar() {
		return radar;
	}
	
	/**
	 * Whether the player is invincible this turn.
	 * @return
	 */
	public boolean isInvincible() {
		return invincibility;
	}
}
