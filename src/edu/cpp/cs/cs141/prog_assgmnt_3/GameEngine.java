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

public class GameEngine {
	private IGameUI ui;
	private int lives;
	private Grid grid;
	
	private GameEngine(IGameUI ui) {
		this.ui = ui;
		lives = Constants.PlayerLives;
		grid = new Grid();
	}

	public static void start(IGameUI ui) {
		GameEngine engine = new GameEngine(ui);
		engine.enterLoop();
	}
	
	private void enterLoop() {
		//TODO while loop for prompting for ui, processing input based on state, requesting UI update 
	}
}
