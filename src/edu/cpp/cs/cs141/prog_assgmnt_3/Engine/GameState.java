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

import java.io.Serializable;

/**
 * Finite states of the game.  This describes how input will be processed.
 */
public enum GameState implements Serializable {
	Menu,
	Saving,
	Loading,
	Playing,
	Moving,
	Shooting,
	PlayingAfterLook,
	Looking,
	Dead,
	Victory,
	Quit
}
