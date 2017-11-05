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
import edu.cpp.cs.cs141.prog_assgmnt_3.GameState;
import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.GameStateException;

public class UI implements IGameUI {
	private Scanner keyboard;

	public UI() {
		keyboard = new Scanner(System.in);
	}

	@Override
	public String getKeyInput(GameState state) throws GameStateException {
		switch (state) {
			case Menu:
				printMainMenu();
				break;
			case Playing:
				
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
		
		return line;
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		printWelcomeMessage();
	}
	
	private static void printWelcomeMessage() {
		System.out.println("Welcome to SpyGame");
	}
	
	private static void printMainMenu() {
		System.out.println("1). New Game");
		System.out.println("2). Load Game");
		System.out.println("4). Help");
		System.out.println("5). Quit");
	}
	
	private static void printHelp() {
		//TODO print help
	}
	
	private static void printInputError() {
		System.out.println("Invalid input.  Please try again.");
	}
}
