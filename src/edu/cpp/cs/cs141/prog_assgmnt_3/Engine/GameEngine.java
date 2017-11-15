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
import edu.cpp.cs.cs141.prog_assgmnt_3.Utilities;
import edu.cpp.cs.cs141.prog_assgmnt_3.ViewDirection;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.PositionException;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.PowerUp;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.PowerUpType;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Room;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObjectSet;
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
			boolean canPrintGame = state == GameState.Playing || state == GameState.PlayingAfterLook || state == GameState.Moving; 
			GameTurnResult result = canPrintGame ? 
					new GameTurnResult(grid.getBoardString(player.getPosition(), lookDirection, debug, player.getRadar()), lives, command) : 
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
		case Moving:
			processMoveInput(input);
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
			default:
				command = UICommand.PrintInputError;
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
			command = UICommand.PrintGame;
			state = GameState.Moving;
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
		default:
			command = UICommand.PrintInputError;
			break;
		}
	}
	
	/**
	 * 
	 * @param input
	 */
	private void processLookingInput(String input) {
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
		default:
			command = UICommand.PrintInputError;
			return;
		}
		
		state = GameState.PlayingAfterLook;
		command = UICommand.PrintGame;
	}
	
	/**
	 * Moves the player according to user input and then moves n i n j a s randomly from processMoveNinja().
	 */
	private void processMoveInput(String input) {
		try {	
			switch(input) {
				case "W":
					grid.move(player, player.getPosition().changeY(player.getPosition().getY() - 1));
					break;
				case "A":
					grid.move(player, player.getPosition().changeX(player.getPosition().getX() - 1));
					break;
				case "S":
					grid.move(player, player.getPosition().changeY(player.getPosition().getY() + 1));
					break;
				case "D":
					grid.move(player, player.getPosition().changeX(player.getPosition().getX() + 1));
					break;
				default:
					command = UICommand.PrintInputError;
					return;
			}
			command = UICommand.PrintGame;
			state = GameState.Playing;
			
			processMoveNinjas();
			
			tryUsePowerUp();
		}
		catch(PositionException ex) {
			command = UICommand.PrintMoveError;
		}
	}
	
	/**
	 *  Moves enemy players to random positions on the grid.
	 */
	private void processMoveNinjas() {
		Random rand = new Random();
		int randomPos;
		for(int i = 0; i < Constants.EnemyCount; i++) {
			Position[] hold = grid.getAdjacent(enemies[i].getPosition());
			boolean hasCollision = false;
			randomPos  = rand.nextInt(4);
			int tries = 0;
			
			//get a valid position that is not taken by a room or other ninja.
			do {
				if (tries >= hold.length)
					break;
				
				tries++;
				
				Position pos = hold[randomPos];
				if (pos == null) {
					hasCollision = true;
					randomPos++;
					if (randomPos == hold.length)
						randomPos = 0;
					continue;
				}
				
				if (hasCollision == false) {
					for (int j = 0; j < rooms.length; j++) {
						if (rooms[j].getPosition().posEquals(pos.getX(), pos.getY())) {
							hasCollision = true;
							break;
						}
					}
				}
				
				if (hasCollision == false) {
					for (int j = 0; j < enemies.length; j++) {
						if (enemies[j].getPosition().posEquals(pos.getX(), pos.getY())) {
							hasCollision = true;
							break;
						}
					}
				}
				
				if (hasCollision) {
					randomPos++;
					if (randomPos == hold.length)
						randomPos = 0;
				}
				
			} while(hasCollision);
			
			try {
				grid.move(enemies[i], hold[randomPos]);					
			}
			catch (Exception ex) {
//				System.out.println("Ninja move error." + enemies[i].getPosition()+"\n\n");
//				for (Position p : hold)
//					System.out.println(p);	
			}	
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
		for (int i = 0; i < Constants.EnemyCount; ++i) {
			Position pos = Utilities.getValidPosition(rand, grid, rooms);
			enemies[i] = new Enemy(pos);
			grid.add(enemies[i], pos);
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
		
		Position pos = Utilities.getValidPosition(rand, grid, rooms);
		grid.add(new PowerUp(pos, PowerUpType.Radar), pos);		
		
		pos = Utilities.getValidPosition(rand, grid, rooms);
		grid.add(new PowerUp(pos, PowerUpType.Invincibility), pos);	
		
		pos = Utilities.getValidPosition(rand, grid, rooms);
		grid.add(new PowerUp(pos, PowerUpType.Ammo), pos);	
	}
	
	/**
	 * 
	 */
	public void tryUsePowerUp() {
		GameObjectSet set = grid.get(player.getPosition());
		GameObject obj = set.getAt(0);
		if (obj == null || obj instanceof PowerUp == false) {
			obj = set.getAt(1);
		}
		
		if (obj == null || obj instanceof PowerUp == false)
			return;
		
		player.usePowerUp((PowerUp)obj);
	}
}

