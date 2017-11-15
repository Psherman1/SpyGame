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
	 * Gets a random valid position within the grid that is not in a room. 
	 * @param rand
	 * @param grid
	 * @param rooms
	 * @return
	 */
	public static Position getValidPosition(Random rand, Grid grid, Room[] rooms, ActiveAgent[] ninjas) {
		int x, y; 
		boolean isValid;
		do {		
			isValid = true;
			x = rand.nextInt(Constants.GridColumns);
			y = rand.nextInt(Constants.GridRows);
			
			for (int j = 0; j < rooms.length; j++) {
				if (rooms[j].getPosition().posEquals(x, y)) {
					isValid = false;
					break;
				}
			}
			
			if (isValid) {
				for (int j = 0; j < ninjas.length; j++) {
					if (ninjas[j] != null && ninjas[j].getPosition().posEquals(x, y)) {
						isValid = false;
						break;
					}
				}
			}
			
		} while (isValid == false);
		
		return new Position(x, y);
	}
}
