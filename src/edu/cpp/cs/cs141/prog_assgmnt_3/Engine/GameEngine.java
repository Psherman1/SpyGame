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
import edu.cpp.cs.cs141.prog_assgmnt_3.CardinalDirection;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
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
	private CardinalDirection lookDirection;
	private int invincibleTurns;
	
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
					new GameTurnResult(
							grid.getBoardString(player.getPosition(),
							lookDirection, 
							debug,
							player.getRadar()),
							lives,
							command,
							new PlayerStatus(player.getRadar(), player.isInvincible(), player.canAttack(), invincibleTurns)) : 
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
		case Saving:
			processSaveInput(input);			
			break;
		case Loading:
			processLoadInput(input);			
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
		case Shooting:
			processShootingInput(input);
			break;
		case Dead:
		case Victory:
			processPostGameInput(input);
			break;
		}
	}
	
	private void processShootingInput(String input) {
		int start, end;
		boolean isX;
		switch (input) {
			case "W":
				start = player.getPosition().getY() - 1;
				end = -1;
				isX = false;
				break;
			case "A":
				start = player.getPosition().getX() - 1;
				end = -1;
				isX = true;
				break;
			case "S":
				start = player.getPosition().getY() + 1;
				end = Constants.GridRows;
				isX = false;
				break;
			case "D":
				start = player.getPosition().getX() + 1;
				end = Constants.GridColumns;
				isX = true;
				break;
			default:
				command = UICommand.PrintInputError;
				return;
		}
		
		//can't shoot into a wall.
		if (start == end) {
			command = UICommand.PrintInputError;
			return;
		}
		
		player.shootGun();
		state = GameState.Playing;
		
		int iterator = start < end ? 1 : -1;
		boolean hitEnemy = false;
		while (hitEnemy == false && (start < end ? start < end : start > end)) {
			Position newPos = isX ? 
				new Position(start, player.getPosition().getY()) :
				new Position(player.getPosition().getX(), start);
			
			if (grid.get(newPos).getCount() > 0) {
				for (Enemy ninja : enemies) {
					if (ninja.getPosition().posEquals(newPos))
					{
						grid.remove(ninja, newPos);
						enemies = removeFromArray(enemies, ninja);
						hitEnemy = true;
						break;
					}
				}
			}
			
			start+= iterator;
		}
		
		//move the ninjas
		processMoveNinjas();
		
		//if any ninjas share the same space as the player, kill the player
		if (checkNinjaCollisions())
			return;
		
		consumeInvincibility();
		
		command = hitEnemy ? command = UICommand.PrintShootHit : UICommand.PrintShootMiss;
	}

	/**
	 * 
	 * @param input
	 */
	private void processPostGameInput(String input) {
		switch (input) {
		case "1":
			state = GameState.Playing;
			command = UICommand.PrintGame;
			resetGame();
			break;
		case "2":
			state = GameState.Loading;
			break;
		case "3":
			state = GameState.Quit;
			command = UICommand.PrintEnd;
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
	private void processMenuInput(String input) {
		switch (input) {
			case "1":
				state = GameState.Playing;
				command = UICommand.PrintGame;
				resetGame();
				break;
			case "2":
				state = GameState.Loading;
				break;
			case "3":
				command = UICommand.PrintHelp;
				break;
			case "4":
				state = GameState.Quit;
				command = UICommand.PrintEnd;
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
			if (player.canAttack() == false) {
				command = UICommand.PrintNoAmmo;
				return;
			}
			
			state = GameState.Shooting;
			break;
		case "5":
			state = GameState.Saving;
			break;
		case "6":
			state = GameState.Loading;
			break;
		case "7":
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
			lookDirection = CardinalDirection.Up;
			break;
		case "A":
			lookDirection = CardinalDirection.Left;
			break;
		case "S":
			lookDirection = CardinalDirection.Down;
			break;
		case "D":
			lookDirection = CardinalDirection.Right;
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
			Position newPos;
			switch(input) {
				case "W":
					newPos = player.getPosition().changeY(player.getPosition().getY() - 1);
					break;
				case "A":
					newPos = player.getPosition().changeX(player.getPosition().getX() - 1);
					break;
				case "S":
					newPos = player.getPosition().changeY(player.getPosition().getY() + 1);
					break;
				case "D":
					newPos = player.getPosition().changeX(player.getPosition().getX() + 1);
					break;
				default:
					command = UICommand.PrintInputError;
					return;
			}
			
			boolean canEnterRoom = false;
			for (Room r : rooms) {
				//we can enter a room and thus don't need to check for it if the new move is in a room
				//and the current position is one space above the room.
				if (r.getPosition().posEquals(player.getPosition().getX(), player.getPosition().getY() + 1) &&
					r.getPosition().posEquals(newPos)) {
					canEnterRoom = true;
					break;
				}
				
				//if the player is inside a room and isn't moving up, this move is invalid.
				if (r.getPosition().posEquals(newPos.getX(), newPos.getY() + 1)  == false &&
					r.getPosition().posEquals(player.getPosition())) {
					command = UICommand.PrintMoveError;
					return;
				}
			}
			
			command = UICommand.PrintGame;
			
			grid.move(player, newPos, canEnterRoom ? null : rooms);
			
			if (checkRoomVictory()) {
				state = GameState.Victory;
				return;
			}
			
			//move the ninjas
			processMoveNinjas();
			
			//if any ninjas share the same space as the player, kill the player
			if (checkNinjaCollisions())
				return;
			
			//try to use a power up if the player is standing on one.
			tryUsePowerUp();
			
			state = GameState.Playing;
			
			consumeInvincibility();
		}
		catch(PositionException ex) {
			command = UICommand.PrintMoveError;
		}
	}
	
	/**
	 * Decrements the invincibility turns if possible.  If there are no turns remaining, the player is set to be no longer invincible. 
	 */
	private void consumeInvincibility() {
		if (invincibleTurns > 0) {
			invincibleTurns--;
			if (invincibleTurns == 0)
				player.disableInvincibilty();				
		}
	}
	
	/**
	 * Checks to see if ninjas are adjacent to the player and can kill him. If so, states are updated. 
	 * @return Returns true if a ninja can kill the player, false otherwise.
	 */
	private boolean checkNinjaCollisions() {
		for (Enemy ninja : enemies) {
			if (player.isInvincible() == false && ninja.getPosition().posEquals(player.getPosition()))
			{
				command = UICommand.PrintDead;
				state = GameState.Playing;
				resetGame();
				
				lives--;
				if (lives == 0) {
					state = GameState.Dead;
					command = UICommand.PrintEnd;
				}
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the player is in a room with a briefcase.
	 * @return
	 */
	private boolean checkRoomVictory() {
		for (Room r : rooms) {
			if (player.getPosition().posEquals(r.getPosition()) && r.hasBriefcase())
				return true;
		}
		
		return false;
	}
	
	/**
	 *  Moves enemy players to random positions on the grid.
	 */
	private void processMoveNinjas() {
		Random rand = new Random();
		int randomPos;
		for(int i = 0; i < enemies.length; i++) {
			Position[] hold = grid.getAdjacent(enemies[i].getPosition());
			boolean hasCollision;
			randomPos  = rand.nextInt(4);
			int tries = 0;
			
			//get a valid position that is not taken by a room or other ninja.
			do {
				if (tries >= hold.length)
					break;
				
				tries++;
				hasCollision = false;
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
				grid.move(enemies[i], hold[randomPos], null);					
			}
			catch (Exception ex) {
//				System.out.println("Ninja move error." + enemies[i].getPosition()+"\n\n");
//				for (Position p : hold)
//					System.out.println(p);	
			}	
		}
	}
	
	/**
	 * 
	 * @param arr
	 * @param obj
	 * @return
	 */
	private Enemy[] removeFromArray(Enemy[] arr, Enemy obj) {
		Enemy[] newArr = new Enemy[arr.length - 1];
		int difference = 0;
		for (int i = 0; i < arr.length; i++)  {
			if (arr[i] == obj) {
				difference = 1;
				continue;
			}
			newArr[i - difference] = arr[i];
		}
		
		return newArr;
	}
	
	
	/**
	 * Sets turn flags to a default value at the beginning of each turn.
	 */
	private void resetTickFlags() {
		command = UICommand.None;
		
		if (state != GameState.PlayingAfterLook)
			lookDirection = CardinalDirection.None;
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
		GameObject[] invalidPositions = new GameObject[rooms.length + enemies.length];
		int ct = 0;
		for (Room r : rooms) {
			invalidPositions[ct] = r;
			ct++;
		}
		
		for (int i = 0; i < Constants.EnemyCount; ++i) {
			Position pos = Utilities.getRandomValidPosition(rand, grid, invalidPositions, player.getPosition(), Constants.StartingPositionTolerance);
			enemies[i] = new Enemy(pos);
			invalidPositions[ct] = enemies[i];
			ct++;
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
		GameObject[] invalidPositions = new GameObject[rooms.length + enemies.length + 2];
		int ct = 0;
		for (Room r : rooms) {
			invalidPositions[ct] = r;
			ct++;
		}
		
		for (Enemy n : enemies) {
			invalidPositions[ct] = n;
			ct++;
		}
		
		Position pos = Utilities.getRandomValidPosition(rand, grid, invalidPositions, player.getPosition(), -1);
		PowerUp p = new PowerUp(pos, PowerUpType.Radar);
		grid.add(p, pos);
		invalidPositions[ct] = p;
		ct++;
		
		pos = Utilities.getRandomValidPosition(rand, grid, invalidPositions, player.getPosition(), -1);
		p = new PowerUp(pos, PowerUpType.Invincibility);
		grid.add(p, pos);	
		invalidPositions[ct] = p;
		ct++;
		
		pos = Utilities.getRandomValidPosition(rand, grid, invalidPositions, player.getPosition(), -1);
		grid.add(new PowerUp(pos, PowerUpType.Ammo), pos);	
	}
	
	/**
	 * Uses a power up if one exists on the same space the player does, and removes it from the grid.
	 */
	public void tryUsePowerUp() {
		GameObjectSet set = grid.get(player.getPosition());
		GameObject obj = set.getAt(0);
		if (obj == null || obj instanceof PowerUp == false) {
			obj = set.getAt(1);
		}
		
		if (obj == null || obj instanceof PowerUp == false)
			return;
		
		PowerUp powerUp = (PowerUp)obj;
		player.usePowerUp(powerUp);
		grid.remove(powerUp, powerUp.getPosition());
		
		if (powerUp.getType() == PowerUpType.Invincibility)
			invincibleTurns = Constants.InvincibleTurns;
	}


	/**
	 * Creates a GameSaveObject based on the current state of the GameEngine.
	 * @return
	 */
	private GameSave createGameSaveObj() {
		// Note... we don't save the UI! If you start a game from the CMD line, we can load it up into a gui.
		GameSave save = new GameSave();
		save.setDebug(debug);
		save.setLives(lives);
		save.setGrid(grid);
		save.setGameState(state);
		save.setPlayer(player);
		save.setEnemies(enemies);
		save.setRoom(rooms);
		save.setUICommand(command);
		save.setCardinalDirection(lookDirection);
		save.setInvincibleTurns(invincibleTurns);
		return save;
	}

	/**
	 * WARNING: THIS DOES NOT LOAD A UI! Caller handles exceptions!
	 * @param filename
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void loadGame(String filename) throws ClassNotFoundException, IOException {
		GameSave savedGame = GameSave.loadGameSaveFromFile(filename);
		debug = savedGame.getDebug();
		lives = savedGame.getLives();
		grid = savedGame.getGrid();
		state = savedGame.getGameState();
		player = savedGame.getPlayer();
		enemies = savedGame.getEnemies();
		rooms = savedGame.getRoom();
		command = savedGame.getUICommand();
		lookDirection = savedGame.getCardinalDirection();
		invincibleTurns = savedGame.getInvincibleTurns();
	}
	
	private void processSaveInput(String input) {
		try {
			GameSave save = createGameSaveObj();
			GameSave.saveGameToFile(input, save);
			command = UICommand.PrintGame;
		}
		catch (IOException e) {
			command = UICommand.PrintIOError;
		}
		
		state = GameState.Playing;
	}
	
	private void processLoadInput(String input) {
		GameSave load = new GameSave();
		try {
			GameSave.loadGameSaveFromFile(input);
		}
		catch (Exception ex) {
			command = UICommand.PrintIOError;
			state = GameState.Menu; //TODO if we were playing before, we want to let them keep playing.
		}
	}
}
