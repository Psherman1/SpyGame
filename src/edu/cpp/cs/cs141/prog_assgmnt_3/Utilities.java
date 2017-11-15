/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #N
 *
 * <description-of-assignment>
 *
 *   Nick Huiting
 */
package edu.cpp.cs.cs141.prog_assgmnt_3;

import java.util.Random;

import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Room;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.VisibilityPriority;

/**
 * Contains static utility methods.
 */
public final class Utilities {
	/**
	 * Returns whether p1 is of a greater priority than p2.  This is in lieu of the lack of operator overloads.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean priorityGreater(VisibilityPriority p1, VisibilityPriority p2) {
		return p1.getLevel() > p2.getLevel();
	}
	
	public static boolean positionLooked(Position playerPosition, Position position, ViewDirection direction) {
		switch (direction) {
		case None:
			return false;
		case Down:
			return playerPosition.getX() == position.getX() && playerPosition.getY() + 2 == position.getY();
		case Left:
			return playerPosition.getX() - 2 == position.getX() && playerPosition.getY() == position.getY();
		case Right:
			return playerPosition.getX() + 2 == position.getX() && playerPosition.getY() == position.getY();
		case Up:
			return playerPosition.getX() == position.getX() && playerPosition.getY() - 2 == position.getY();
		default:
			return false;
		}
	}
	
	public static Position getValidPosition(Random rand, Grid grid, Room[] rooms) {
		int randomPosX, randomPosY; 
		do {													
			randomPosX = rand.nextInt(Constants.GridColumns);
			randomPosY = rand.nextInt(Constants.GridRows);
		} while (grid.validSpawn(randomPosX, randomPosY) == false);
		return new Position(randomPosX, randomPosY);
	}
}
