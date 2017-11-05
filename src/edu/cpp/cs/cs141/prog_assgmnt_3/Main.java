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

import edu.cpp.cs.cs141.prog_assgmnt_3.UI.IGameUI;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UI;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		System.out.println("Hello");
=======
		IGameUI ui = new UI();
<<<<<<< HEAD
		
		try {
			GameEngine.start(ui);
		} 
		catch (Exception e) {
			System.out.println("Unrecoverable Exception encountered.  The game will now exit.\n\n");
			e.printStackTrace();
			return;
		}
=======
		GameEngine.start(ui);
>>>>>>> abd5d98772cb2dbbca934e7648df814f0eeae5b6
>>>>>>> origin/master
	}
}
