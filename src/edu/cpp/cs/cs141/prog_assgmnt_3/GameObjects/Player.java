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

import edu.cpp.cs.cs141.prog_assgmnt_3.Constants;
import edu.cpp.cs.cs141.prog_assgmnt_3.Gun;
import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * This class represent the player. It extends ActiveAgent.
 */
public class Player extends ActiveAgent {
	private Gun gun = new Gun();
	private boolean invincibility;
	private boolean radar;
	
	/**
	 * 
	 */
	public Player() {
		super(new Position(0, Constants.GridRows - 1));
	}
	
	/**
	 * 
	 */
	public void shootGun() {
		gun.setAmmo(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean canAttack() {
		return gun.hasAmmo();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getRadar() {
		return radar;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isInvincible() {
		return invincibility;
	}
	
	/**
	 * 
	 */
	public void disableInvincibilty() {
		invincibility = false;
	}
	
	/**
	 * 
	 */
	private void ammoPowerUp() {
		gun.setAmmo(1);
	}
	
	/**
	 * 
	 */
	private void radarPowerUp() {
		radar = true;
	}
	
	/**
	 * 
	 */
	private void invincibilityPowerUp() {
		invincibility = true;
	}

	/**
	 * Uses a power up.
	 * @param powerUp
	 */
	public void usePowerUp(PowerUp powerUp) {
		switch (powerUp.getType()) {
		case Radar:
			radarPowerUp();
		case Ammo:
			ammoPowerUp();
			break;
		case Invincibility:
			invincibilityPowerUp();
			break;
		default:
			break;
			
		}
	}
	
	/**
	 * 
	 * @param debug
	 * @param radar
	 * @return
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return 'P';
	}
}
