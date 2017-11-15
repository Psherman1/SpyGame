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
import edu.cpp.cs.cs141.prog_assgmnt_3.Position;
import edu.cpp.cs.cs141.prog_assgmnt_3.ViewDirection;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.PowerUp;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.PowerUpType;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Room;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.IGameUI;
import edu.cpp.cs.cs141.prog_assgmnt_3.UI.UICommand;
import java.util.Random;

public class GameEngine {
	private boolean debug;
	
	private IGameUI ui;
	private int lives;
	private Grid grid;
	private GameState state;
	private Player player;
	private Enemy[] enemies;
	private Room[] rooms;
	private PowerUp[] powerups;
	private UICommand command;
	
	private ViewDirection lookDirection;
	
	/**
	 * 
	 * @param ui
	 */
	private GameEngine(IGameUI ui) {
		this.ui = ui;
		state = GameState.Menu;
	}

	/**
	 * 
	 * @param ui
	 * @throws GameStateException
	 */
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
			//set necessary flags to default values before asking for input.
			resetTickFlags();
			
			//prompt the user interface for input.
			String input = ui.getKeyInput(state);
			
			//process the input based on the same state, execute the turn.
			processInput(input);
			
			//Create a result of the turn.
			boolean canPrintGame = state == GameState.Playing || state == GameState.PlayingAfterLook; 
			GameTurnResult result = canPrintGame ? 
					new GameTurnResult(grid.getBoardString(player.getPosition(), lookDirection, debug), lives, command) : 
					new GameTurnResult(command);
					
			//Request that the user interface update given the result of the turn.
			ui.updateUI(result);
		} while (state != GameState.Quit);
	}
	
	/**
	 * 
	 * @param input
	 */
	private void processInput(String input) {
		switch (state) {
		case Menu:
			processMenuInput(input);
			break;
		case Playing:
		case PlayingAfterLook:
			procesPlayingInput(input);
			break;
		case Looking:
			processLookingInput(input);
			break;
		}
	}
	
	/**
	 * 
	 * @param input
	 */
	private void processMenuInput(String input) {
		switch (input) {
		case "1":
			state = GameState.Playing;
			command = UICommand.PrintGame;
			resetGame();
			break;
		case "2":
			//TODO load
			break;
		case "3":
			command = UICommand.PrintHelp;
			break;
		case "4":
			state = GameState.Quit;
			break;
		}
	}
	
	/**
	 * 
	 * @param input
	 */
	private void procesPlayingInput(String input) {
		switch (input) {
		case "1":
			if (state == GameState.PlayingAfterLook) {
				command = UICommand.PrintAlreadyLooked;
				return;
			}
				
			command = UICommand.PrintLook;
			state = GameState.Looking;
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
	
	/**
	 * 
	 * @param input
	 */
	private void processLookingInput(String input) {
		state = GameState.PlayingAfterLook;
		command = UICommand.PrintGame;
		
		switch (input) {
		case "W":
			lookDirection = ViewDirection.Up;
			break;
		case "A":
			lookDirection = ViewDirection.Left;
			break;
		case "S":
			lookDirection = ViewDirection.Down;
			break;
		case "D":
			lookDirection = ViewDirection.Right;
			break;
		}
	}
	
	/**
	 * Sets turn flags to a default value at the beginning of each turn.
	 */
	private void resetTickFlags() {
		command = UICommand.None;
		
		if (state != GameState.PlayingAfterLook)
			lookDirection = ViewDirection.None;
	}
	
	/**
	 * Resets all game objects and creates a new randomized game board.
	 */
	private void resetGame() {
		lives = Constants.PlayerLives;
		grid = new Grid();
		player = new Player();
		enemies = new Enemy[Constants.EnemyCount];
		rooms = new Room[Constants.RoomCount];
		grid.add(player, player.getPosition());
		resetRooms();
		resetEnemies();
		resetPowerups();
	}

	/**
	 * Initiates all the possible rooms on the grid.
	 * One random room will be selected to have the briefcase. 
	 * That room will have its hasBriefcase boolean value
	 * set to true.
	 */
	private void resetRooms() {
		Position pos = new Position(1,1);
		rooms[0] = new Room(pos);
		grid.add(rooms[0], rooms[0].getPosition());

		pos = new Position(1,4);
		rooms[1] = new Room(pos);
		grid.add(rooms[1], rooms[1].getPosition());
		
		pos = new Position(1,7);
		rooms[2] = new Room(pos);
		grid.add(rooms[2], rooms[2].getPosition());
		
		pos = new Position(4,1);
		rooms[3] = new Room(pos);
		grid.add(rooms[3], rooms[3].getPosition());
		
		pos = new Position(4,4);
		rooms[4] = new Room(pos);
		grid.add(rooms[4], rooms[4].getPosition());
		
		pos = new Position(4,7);
		rooms[5] = new Room(pos);
		grid.add(rooms[5], rooms[5].getPosition());
		
		pos = new Position(7,1);
		rooms[6] = new Room(pos);
		grid.add(rooms[6], rooms[6].getPosition());
		
		pos = new Position(7,4);
		rooms[7] = new Room(pos);
		grid.add(rooms[7], rooms[7].getPosition());
		
		pos = new Position(7,7);
		rooms[8] = new Room(pos);
		grid.add(rooms[8], rooms[8].getPosition());

		Random rand = new Random();
		int briefCaseRoom = rand.nextInt(rooms.length);
		rooms[briefCaseRoom].setHasBriefcaseTrue();	
	}
	
	/* In progress, more efficient alternative to resetRooms()
	private void resetRooms() {
		int tempArray[] = {1, 4, 7, 4, 1};
		Position pos;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				pos = new Position(tempArray[i], tempArray[j]);
				grid.add(new Room(pos), pos);
			}
		}
		
		Random rand = new Random(); // TODO briefcase isn't working yet
		pos = new Position(tempArray[rand.nextInt(3)], tempArray[rand.nextInt(3)]);
		grid.add(new Room(pos, true), pos);
		
	}
	*/
	
	/**
	 * Adds enemies to valid random locations.
	 * The coordinates are first randomized then checked for validity
	 * through validSpawn method in grid class. If valid = returned true, they will be assigned
	 * created to the grid. Otherwise, a new pair of coordinates are randomized
	 * until a valid location is found.
	 */
	private void resetEnemies() {
		Random rand = new Random();
		int randomPosX, randomPosY; 
		for (int i = 0; i < Constants.EnemyCount; ++i) {
			do {
				randomPosX = rand.nextInt(Constants.GridColumns);
				randomPosY = rand.nextInt(Constants.GridRows);
			} while (grid.validSpawn(randomPosX, randomPosY) == false);	
			
			Position pos = new Position(randomPosX, randomPosY);
			enemies[i] = new Enemy(pos);
			grid.add(enemies[i], enemies[i].getPosition());
		}
	}
	
	/**
	 * Adds power ups to valid random locations.
	 * The coordinates are first randomized then checked for validity
	 * through validSpawn method in grid class. If valid = returned true, they will be
	 * assigned to the grid. Otherwise, a new pair of coordinates are randomized
	 * until a valid location is found.
	 */
	private void resetPowerups() {
		Random rand = new Random();
		int randomPosX, randomPosY; 
		do {													
			randomPosX = rand.nextInt(Constants.GridColumns);
			randomPosY = rand.nextInt(Constants.GridRows);
		} while (grid.validSpawn(randomPosX, randomPosY) == false);
		Position pos = new Position(randomPosX, randomPosY);
		grid.add(new PowerUp(pos, PowerUpType.Radar), pos);		
		
		do {													
			randomPosX = rand.nextInt(Constants.GridColumns);
			randomPosY = rand.nextInt(Constants.GridRows);
		} while (grid.validSpawn(randomPosX, randomPosY) == false);
		pos = new Position(randomPosX, randomPosY);
		grid.add(new PowerUp(pos, PowerUpType.Invincibility), pos);	
		
		do {												
			randomPosX = rand.nextInt(Constants.GridColumns);
			randomPosY = rand.nextInt(Constants.GridRows);
		} while (grid.validSpawn(randomPosX, randomPosY) == false);
		pos = new Position(randomPosX, randomPosY);
		grid.add(new PowerUp(pos, PowerUpType.Ammo), pos);	
	}
}

