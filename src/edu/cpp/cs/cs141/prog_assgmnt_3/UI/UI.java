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
				
				break;
			case Victory:
				
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
			default:
				break;
		}
	}
	
	private void printGame(GameTurnResult result) {
		for (String line : result.getGridLines())
			System.out.println(line);
		
		System.out.println("Player lives: " + result.getLives() + "/" + Constants.PlayerLives);
	}

	@Override
	public void initialize() {
		printWelcomeMessage();
	}
	
	private static void printWelcomeMessage() {
		System.out.println("Welcome to SpyGame");//TODO better welcome
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
		System.out.println("You just entered a pitch-black square room of length" + Constants.GridColumns + " for each side.");
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
		System.out.println("	- Radar: display on screen the location of the briefcase.");
		System.out.println("You start the game at the bottom left corner of the room.");
		System.out.println("Good luck, have fun.");
	}
	
	private static void printInputError() {
		System.out.println("Invalid input.  Please try again.");
	}
	private static void printMoveError() {
		System.out.println("Invalid move.");
	}
}

