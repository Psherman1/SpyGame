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

import edu.cpp.cs.cs141.prog_assgmnt_3.GameEngine;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameState;

public class UI implements IGameUI {
	private Scanner keyboard;

	public UI() {
		keyboard = new Scanner(System.in);
	}

	@Override
	public String getKeyInput(GameState state) {
		//TODO switch state, print different prompt menu based on state
		
		return null;
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		printWelcomeMessage();
		printMainMenu();
	}
	
	private static void printWelcomeMessage() {
		
	}
	
	private static void printMainMenu() {
		
	}
	
	private static void printHelp() {
		
	}
}
