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

import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
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
	
	public static boolean positionLooked(Position playerPosition, Position position, CardinalDirection direction) {
		switch (direction) {
		case None:
			return false;
		case Down:
			return position.posEquals(playerPosition.getX(), playerPosition.getY() + 2);
		case Left:
			return position.posEquals(playerPosition.getX() - 2, playerPosition.getY());
		case Right:
			return position.posEquals(playerPosition.getX() + 2, playerPosition.getY());
		case Up:
			return position.posEquals(playerPosition.getX(), playerPosition.getY() - 2);
		default:
			return false;
		}
	}
	
	/**
	 * Gets a random valid position within the grid that is not in a room, on a ninja's space,
	 * or within a tolerance of an invalid position.
	 * 
	 *  If a value less than 0 is passed for the invalid tolerance, the invalid position will be ignored.
	 * @param rand
	 * @param grid
	 * @param invalidPositions
	 * @param invalidPosition
	 * @param tolerance Tolerance from the invalid position that invalidates new positions.  
	 * @return
	 */
	public static Position getRandomValidPosition(Random rand, Grid grid, GameObject[] invalidPositions, 
			Position invalidPosition, int tolerance) {
		int x, y; 
		boolean isValid;
		do {		
			isValid = true;
			x = rand.nextInt(Constants.GridColumns);
			y = rand.nextInt(Constants.GridRows);
			
			for (int j = 0; j < invalidPositions.length; j++) {
				if (invalidPositions[j] != null && invalidPositions[j].getPosition().posEquals(x, y)) {
					isValid = false;
					break;
				}
			}
			
			if (isValid && tolerance > 0) {
				if (Math.abs(x - invalidPosition.getX()) <= tolerance && Math.abs(y - invalidPosition.getY()) <= tolerance)
					isValid = false;
			}
			
		} while (isValid == false);
		
		return new Position(x, y);
	}
}
