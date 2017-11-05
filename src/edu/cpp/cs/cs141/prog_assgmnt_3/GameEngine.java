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

import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.IGameUI;

public class GameEngine {
	private IGameUI ui;
	private int lives;
	private Grid grid;
	private GameState state;
	
	private GameEngine(IGameUI ui) {
		this.ui = ui;
		lives = Constants.PlayerLives;
		grid = new Grid();
		state = GameState.Menu;
	}

	public static void start(IGameUI ui) throws GameStateException {
		GameEngine engine = new GameEngine(ui);
		engine.enterLoop();
	}
	
	/**
	 * Executes the main game loop.  The loop will do three things in order:
	 * 1. Request input from the user via the interface.
	 * 2. Process that input based on the game state.  This is where game logic will be executed.
	 * 3. Request that the interface update to reflect the current game state.
	 * @throws GameStateException 
	 */
	private void enterLoop() throws GameStateException {
		ui.initialize();
		do {
			String input = ui.getKeyInput(state);
			processInput(input);
			ui.updateUI();
		} while (state != GameState.Quit);
	}
	
	private void processInput(String input) {
		switch (state) {
		case Menu:
			processMenuInput(input);
			break;
		case Playing:
			processMenuInput(input);
			break;
		}
	}
	
	private void processMenuInput(String input) {
		switch (input) {
		case "5":
			state = GameState.Quit;
			break;
			
		}
	}
}
