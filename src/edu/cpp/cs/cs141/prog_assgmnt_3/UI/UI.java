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
				printDirectionMenu();
				break;
			case Moving:
				printDirectionMenu();
				break;
			case Dead:
				printDeath();
				break;
			case Victory:
				printVictory();
				printEnd();
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
					System.out.println("\tRadar: " + (result.getStatus().hasRadar() ? "Enabled" : "Disabled"));
					continue;
				case 4:
					System.out.println("\tInvincibility: " + (result.getStatus().isInvincible() ? "Enabled" : "Disabled"));
					continue;
				case 5:
					System.out.println("\tAmmo: " + (result.getStatus().hasAmmo() ? "1" : "0"));
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
		System.out.println("*******************");
		System.out.println("Welcome to SpyGame");//TODO better welcome
		System.out.println("*******************\n");
	}
	
	private static void printMainMenu() {
		System.out.println("1). New Game");
		System.out.println("2). Load Game");
		System.out.println("3). Help");
		System.out.println("4). Quit");
	}
	
	private static void printPlayingMenu() {
		System.out.println("1). Look");
		System.out.println("2). Move");
		System.out.println("3). Menu");
		System.out.println("4). Save");
		System.out.println("5). Load");
		System.out.println("6). Toggle Debug");
	}
	
	private static void printLook() {
		System.out.println("Choose a direction to look.");
	}
	
	private static void printAlreadyLooked() {
		System.out.println("You may only look once per turn.");
	}
	
	private static void printDirectionMenu() {
		System.out.println("W). Up");
		System.out.println("A). Left");
		System.out.println("S). Down");
		System.out.println("D). Right");
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

