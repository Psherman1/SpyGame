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

package edu.cpp.cs.cs141.prog_assgmnt_3.UI;

import java.util.Scanner;

import edu.cpp.cs.cs141.prog_assgmnt_3.Constants;
import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameState;
import edu.cpp.cs.cs141.prog_assgmnt_3.Engine.GameTurnResult;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;

public class UI implements IGameUI {
	private Scanner keyboard;

	public UI() {
		keyboard = new Scanner(System.in);
	}

	@Override
	public String getKeyInput(GameState state) throws GameStateException {
		
		//prompt for input
		switch (state) {
			case Menu:
				printMainMenu();
				break;
			case Playing:
			case PlayingAfterLook:
				printPlayingMenu();
				break;
			case Looking:
			case Moving:
			case Shooting:
				printDirectionMenu();
				break;
			case Dead:
				printGameOverMenu(false);
				break;
			case Victory:
				printGameOverMenu(true);
				break;
			default:
				throw new GameStateException("Cannot process input in given state.", state);
		}
		
		String line = keyboard.nextLine();
		while (line.length() != 1) {
			printInputError();
			line = keyboard.nextLine();
		}
		
		return line.toUpperCase();
	}

	/*
	 * Based on a UI command, update the UI.
	 */
	@Override
	public void updateUI(GameTurnResult result) {
		switch (result.getCommand()) {
			case None:
				return;
			case PrintGame:
				printGame(result);
				break;
			case PrintHelp:
				printHelp();
				return;
			case PrintLook:
				printLook();
				return;
			case PrintAlreadyLooked:
				printAlreadyLooked();
				printGame(result);
				return;
			case PrintMoveError:
				printMoveError();
				break;
			case PrintInputError:
				printInputError();
				break;
			case PrintShootHit:
				printShotResult(true);
				printGame(result);
				break;
			case PrintShootMiss:
				printShotResult(false);
				printGame(result);
				break;
			case PrintNoAmmo:
				printNoAmmo();
				break;
			case PrintEnd:
				printEnd();
				break;
			case PrintDead:
				printDeath();
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 * @param result
	 */
	private void printGame(GameTurnResult result) {
		for (int i = 0; i < result.getGridLines().length; i++) {
			System.out.print(result.getGridLines()[i]);
			
			switch (i) {
				case 1:
					System.out.println("\tLives: " + result.getLives() + "/" + Constants.PlayerLives);
					continue;
				case 2:
					System.out.println();
					continue;
				case 3:
					System.out.println("\t(R)adar: " + (result.getStatus().hasRadar() ? "Enabled" : "Disabled"));
					continue;
				case 4:
					System.out.println("\t(I)nvincibility: " + (result.getStatus().isInvincible() ? ("Enabled (" + result.getStatus().getInvincibleTurns() + " turns remaining)") : "Disabled"));
					continue;
				case 5:
					System.out.println("\t(A)mmo: " + (result.getStatus().hasAmmo() ? "1" : "0"));
					continue;
			}
			
			System.out.println();
		}
	}

	@Override
	public void initialize() {
		printWelcomeMessage();
	}
	
	private static void printWelcomeMessage() {
		String message = "║ Welcome to Spy Game ║";
		String border = "";
		for (int i = 0; i < message.length() - 2; i++)
			border += "═";
			
		System.out.println("╔" + border + "╗");
		System.out.println(message);
		System.out.println("╚" + border + "╝");
	}
	
	private static void printMainMenu() {
		System.out.println("1). New Game\t4). Quit");
		System.out.println("2). Load Game");
		System.out.println("3). Help");
	}
	
	private static void printPlayingMenu() {
		System.out.println("1). Look\t4). Shoot\t7). Toggle Debug");
		System.out.println("2). Move\t5). Save\t");
		System.out.println("3). Menu\t6). Load");
	}
	
	private static void printGameOverMenu(boolean won) {
		if (won)
			printVictory();
		else
			printDeath();
		
		System.out.println("1). New Game");
		System.out.println("2). Load Game");
		System.out.println("3). Quit");
	}
	
	private static void printDirectionMenu() {
		System.out.println("W). Up\tD). Right");
		System.out.println("A). Left");
		System.out.println("S). Down");
	}
	
	private static void printLook() {
		System.out.println("Choose a direction to look.");
	}
	
	private static void printAlreadyLooked() {
		System.out.println("You may only look once per turn.");
	}
	
	private static void printHelp() {
		System.out.println("You just entered a pitch-black square room of length " + Constants.GridColumns + " for each side.");
		System.out.println("Your goal is to bypass the ninjas to get to a closet with a briefcase.");
		System.out.println("The closets can only be accessed from the North side.");
		System.out.println("At the beginning of each turn, you can choose to 'look' at any cardinal direction ");
		System.out.println("and then perform either of the following tasks: ");
		System.out.println("	- Move one square in any direction");
		System.out.println("	- If there is still ammo left, shoot in any direction");
		System.out.println("After your turn is finished, the ninjas' turn starts.");
		System.out.println("They can execute you if you are at an adjacent spot from them.");
		System.out.println("You have three lives at the start of the game.");
		System.out.println("There are also three types of power-ups:");
		System.out.println("	- Additional bullet: grants one bullet");
		System.out.println("	- Invincibility: ninjas cannot kill you for 5 turns.");
		System.out.println("	- Radar: display on screen the location of the briefcase.\n");
		System.out.println("You start the game at the bottom left corner of the room.");
		System.out.println("Good luck, have fun.\n");
	}
	
	private static void printInputError() {
		System.out.println("Invalid input.  Please try again.");
	}
	
	private static void printMoveError() {
		System.out.println("Invalid move.");
	}
	
	private static void printShotResult(boolean hit) {
		System.out.println("Shot " + (hit ? "hit!" : "missed."));
	}
	
	private static void printNoAmmo() {
		System.out.println("You cannot fire your gun because you have no ammo.");
	}
	
	private static void printDeath() {
		System.out.println("You died, you lost one life.");
	}
	
	private static void printVictory() {
		System.out.println("Congratulations. You found the briefcase !!!");
		System.out.println("You won !!!");
	}
	
	private static void printEnd() {
		System.out.println("Thank you for playing :)");
	}
}

