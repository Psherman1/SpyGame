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

import edu.cpp.cs.cs141.prog_assgmnt_3.Constants;
import edu.cpp.cs.cs141.prog_assgmnt_3.Gun;
import edu.cpp.cs.cs141.prog_assgmnt_3.Position;

/**
 * @author Jose Rodriguez
 * This class represent the player. It extends ActiveAgent.
 */
public class Player extends ActiveAgent implements Serializable  {
	/**
	 * The player's gun.
	 */
	private Gun gun = new Gun();
	
	/**
	 * Allows the player to become invincible to enemies when invincibility is picked up.
	 */
	private boolean invincibility;
	
	/**
	 * Radar allows the player to see further distance when the power up is picked up.
	 */
	private boolean radar;
	
	/**
	 * Createa a new player.  Sets the default position of player (bottom left corner of the grid).
	 */
	public Player() {
		super(new Position(0, Constants.GridRows - 1));
	}
	
	/**
	 * Shoots the gun.
	 * After shooting the ammo is set to zero.
	 */
	public void shootGun() {
		gun.setAmmo(0);
	}
	
	/**
	 * Checks if the player can attack depending on the amount of ammo the gun carries.
	 * If it has ammo the player can shoot, if not the player cannot shoot.
	 * @return the boolean type of hasAmmo form the gun class.
	 */
	public boolean canAttack() {
		return gun.hasAmmo();
	}
	
	/**
	 * getRadar is used to find out if the player has picked up the radar power up.
	 * @return the boolean value of the radar field.
	 */
	public boolean getRadar() {
		return radar;
	}
	
	/**
	 * isInvincible is used to find out if the player has picked up the invincibility power up.
	 * @return the boolean value of the invincibility field.
	 */
	public boolean isInvincible() {
		return invincibility;
	}
	
	/**
	 * Disables the invincibility the player attains from picking up the invincibility power up.
	 * Sets the invincibility field to false.
	 */
	public void disableInvincibilty() {
		invincibility = false;
	}
	
	/**
	 * Sets the integer value of ammo to 1 when the ammo power up is picked up by player.
	 */
	private void ammoPowerUp() {
		gun.setAmmo(1);
	}
	
	/**
	 * Sets the boolean value of radar to true when the radar power up is picked up.
	 */
	private void radarPowerUp() {
		radar = true;
	}
	
	/**
	 * Sets the boolean value of invincibility to true when the invincibility is picked up.
	 */
	private void invincibilityPowerUp() {
		invincibility = true;
	}

	/**
	 * Uses a power up depending on the type picked up.
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
	 * Returns the player's symbol.
	 * @param debug used for showing the symbols while in debug mode.  Ignored.
	 * @param radar used to show the player symbol.  Ignored.
	 * @return the char value of the symbol that represents Player.
	 */
	@Override
	public char getSymbol(boolean debug, boolean radar) {
		return 'P';
	}
	
	/**
	 * Players are set to always render.
	 */
	@Override
	public VisibilityPriority getPriority() {
		return VisibilityPriority.AlwaysRender;
	}
}
