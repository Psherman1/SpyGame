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

package edu.cpp.cs.cs141.prog_assgmnt_3;

import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.PositionException;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Enemy;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObjectSet;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.Player;

/**
 * Contains all game objects.
 * Assumes top left corner is 0,0
 * Column values represent X values.
 * Row values represent Y values.
 */
public class Grid {
	private GameObjectSet[][] grid;
	
	public Grid() {
		clear();
		
		for (int i = 0; i < Constants.GridColumns; i++) {
			for (int j = 0; j < Constants.GridRows; j++) {
				grid[i][j] = new GameObjectSet();
			}
		}
	}
	
	/**
	 * @param obj
	 * @param pos
	 */
	public void add(GameObject obj, Position pos) {
		grid[pos.getX()][pos.getY()].add(obj);
	}
	
	/**
	 * 
	 * @param obj
	 * @param pos
	 */
	public void remove(GameObject obj, Position pos) {
		grid[pos.getX()][pos.getY()].remove(obj);
	}
	
	/**
	 * 
	 */
	public void clear() {
		grid = new GameObjectSet[Constants.GridColumns][Constants.GridRows];
	}
	
	/**
	 * Creates a string for each row in the board.
	 * @return
	 */
	public String[] getBoardString(Position playerPosition, CardinalDirection viewDirection, boolean debug, boolean radar) {
		String[] lines = new String[Constants.GridRows + 1];
		String header = "   ";
		for (int i = 1; i <= Constants.GridColumns; i++)
			header += i + "  ";
		
		lines[0] = header;
		
		for (int i = 0; i < Constants.GridRows; i++) {
			String line = (i + 1) + "";
			line += " ";
			for (int j = 0; j < Constants.GridColumns; j++) {
				boolean isLooking = Utilities.positionLooked(playerPosition, new Position(j, i), viewDirection);
				line += "[" + grid[j][i].getSymbol(isLooking || playerPosition.isAdjacent(j, i) || playerPosition.posEquals(j, i), debug, radar) + "]";
			}
			lines[i + 1] = line;
		}
		
		return lines;
	}

	/**
	 *
	 * @param pos position of an item to get from the grid
	 * @return GameObjectSet at that space.
	 */
	public GameObjectSet get(Position pos) {
		return grid[pos.getX()][pos.getY()];
	}

	/**
	 * Searches the grid for the specified object. Returns a position.
	 */
	public Position search(GameObject obj) {
		//TODO we don't need this since the objects have positions stored in them already.
		for (int i = 0; i < Constants.GridRows; ++i) {
			for (int j = 0 ; j < Constants.GridColumns; ++j) {
				GameObjectSet set = grid[i][j];
				if (set.search(obj)) {
					return new Position(i,j);
				}
			}
		}
		return null;
	}

	/**
	 * Helper function to get valid adjacent indexes from the grid. If the position is not valid, it will
	 * be null. For example: [null, topPos, rightPos, bottomPos], the leftPos is not valid. Also, diagonal
	 * cells do not count as neighbors, Only cells directly above, below, left, and right.
	 * @param pos Position of the index to get the valid neighbors of.
	 * @return Returns an array of valid positions in the form of [leftPos, topPos, rightPos, bottomPos]
	 */
	public Position[] getAdjacent(Position pos) {
		Position[] posArray = new Position[4];

		int leftX = pos.getX() - 1;
		int topY = pos.getY() - 1;
		int rightX = pos.getX() + 1;
		int bottomY = pos.getY() + 1;

		if(leftX >= 0)
			posArray[0] = new Position(leftX, pos.getY());

		if(topY >= 0)
			posArray[1] = new Position(pos.getX(), topY);

		if(rightX < Constants.GridColumns)
			posArray[2] = new Position(rightX, pos.getY());

		if(bottomY < Constants.GridRows)
			posArray[3] = new Position(pos.getX(), bottomY);

		return posArray;
	}

	/**
	 * Function to move an agent to a specific location. Updates the grid by removing the old location,
	 * and updating the index.
	 * @param agent The agent to move.
	 * @param pos The new position to move to.
	 * @param invalidObjects An array of objects that cannot share the same space as the new position. Can be null.
	 */
	public void move(ActiveAgent agent, Position pos, GameObject[] invalidObjects) throws PositionException {
		if (validatePos(pos) == false)
			throw new PositionException("Invalid position.", pos);
		
		if (invalidObjects != null) {
			for (int i = 0; i < invalidObjects.length; i++) {
				if (invalidObjects[i].getPosition().posEquals(pos))
					throw new PositionException("Invalid position.", pos);
			}
		}
		
		grid[agent.getPosition().getX()][agent.getPosition().getY()].remove(agent);
		agent.move(pos);
		grid[pos.getX()][pos.getY()].add(agent);
	}

	/**
	 * Helper function to check if a position is valid.
	 * @param pos
	 * @return
	 */
	public boolean validatePos(Position pos) {
		return pos.getX() >= 0 && pos.getX() < Constants.GridColumns && pos.getY() >= 0 && pos.getY() < Constants.GridRows;
	}
}
