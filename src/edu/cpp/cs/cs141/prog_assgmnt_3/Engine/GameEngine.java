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

import edu.cpp.cs.cs141.prog_assgmnt_3.Constants;
import edu.cpp.cs.cs141.prog_assgmnt_3.Grid;
import edu.cpp.cs.cs141.prog_assgmnt_3.ViewDirection;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.IGameUI;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UICommand;

public class GameEngine {
	private boolean debug;
	
	private IGameUI ui;
	private int lives;
	private Grid grid;
	private GameState state;
	private Player player;
	private Enemy[] enemies;
	private UICommand command;
	
	private GameEngine(IGameUI ui) {
		this.ui = ui;
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
			command = UICommand.None;
			
			String input = ui.getKeyInput(state);
			processInput(input);
			
			boolean canPrintGame = state == GameState.Playing; 
			GameTurnResult result = canPrintGame ? 
					new GameTurnResult(grid.getBoardString(player.getPosition(), ViewDirection.None, debug), lives, command) : 
					new GameTurnResult(command);
			ui.updateUI(result);
		} while (state != GameState.Quit);
	}
	
	private void processInput(String input) {
		switch (state) {
		case Menu:
			processMenuInput(input);
			break;
		case Playing:
			procesPlayingInput(input);
			break;
		}
	}
	
	private void processMenuInput(String input) {
		switch (input) {
		case "1":
			state = GameState.Playing;
			command = UICommand.PrintGame;
			resetGame();
			break;
		case "2":
			//TODO load save
			break;
		case "3":
			command = UICommand.PrintHelp;
			break;
		case "4":
			state = GameState.Quit;
			break;
		}
	}
	
	private void procesPlayingInput(String input) {
		switch (input) {
		case "1":
			state = GameState.Playing;
			resetGame();
			break;
		case "2":
			//TODO move
			break;
		case "3":
			state = GameState.Menu;
			break;
		case "4":
			//TODO save
			break;
		case "5":
			//TODO load
			break;
		case "6":
			debug = !debug;
			command = UICommand.PrintGame;
			break;
		}
	}
	
	private void resetGame() {
		lives = Constants.PlayerLives;
		grid = new Grid();
		player = new Player();
		enemies = new Enemy[Constants.EnemyCount];
		
		grid.add(player, player.getPosition());
	}
}
