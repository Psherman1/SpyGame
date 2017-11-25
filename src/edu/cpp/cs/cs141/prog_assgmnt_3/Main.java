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

import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameEngine;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.IGameUI;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UI;

public class Main {

	/**
	 * Entrypoint to launch the game.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		IGameUI ui = new UI();
		
		try {
			GameEngine.start(ui);
		} 
		catch (Exception e) {
			System.out.println("Unrecoverable Exception encountered.  The game will now exit.\n\n");
			e.printStackTrace();
			return;
		}
	}
}
