/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment FinalProject
 *
 * A simple yet interesting text-based game where a user is trapped in a square room and must locate 
 * a briefcase hidden in a room, all while avoiding ninja assassins and using power ups to survive.
 * The game is turn based, and allows for loading and saving progress.
 *
 *	 Team Members (Broncodes):
 *   Nick Huiting
 *   Jose Rodriguez
 *   Thanh Doan
 *   Tenzin Tashitsang
 *   Dennis Jimenez
 *   Michael Ackerman
 */
package edu.cpp.cs.cs141.prog_assgmnt_3;

import java.io.Serializable;

import edu.cpp.cs.cs141.prog_assgmnt_3.Exceptions.PositionException;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObjectSet;

/**
 * Contains all game objects. Assumes top left corner is 0,0. Column values represent X values. Row values
 * represent Y values. Each cell contains a GameObjectSet that allows for multiple GameObjects to be stored
 * within a single cell.
 */
public class Grid implements Serializable {
	private GameObjectSet[][] grid;

	/**
	 * Default Constructor for Grid. Creates a 2D array of GameObjectSets. Grid size based on the values set in
	 * Constants.java
	 */
	public Grid() {
		clear();
		for (int i = 0; i < Constants.GridColumns; i++) {
			for (int j = 0; j < Constants.GridRows; j++) {
				grid[i][j] = new GameObjectSet();
			}
		}
	}
	
	/**
	 * Add method to insert a GameObject into a cell of the grid. Multiple objects can reside in a single cell.
	 * @param obj GameObject to be inserted.
	 * @param pos position where GameObject should be inserted.
	 */
	public void add(GameObject obj, Position pos) {
		grid[pos.getX()][pos.getY()].add(obj);
	}
	
	/**
	 * Method to remove a GameObject from a specific cell.
	 * @param obj The GameObject to be removed.
	 * @param pos Position where the GameObject should be removed.
	 */
	public void remove(GameObject obj, Position pos) {
		grid[pos.getX()][pos.getY()].remove(obj);
	}

	/**
	 * Helper method to reset the grid. Sets the grid to a new empty grid.
	 */
	public void clear() {
		grid = new GameObjectSet[Constants.GridColumns][Constants.GridRows];
	}
	
	/**
	 * Creates a string representation for each row in the board.
	 * @return Returns an array of strings where each index is a String representation of the board.
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
	 * Getter for a grid cell.
	 * @param pos position of the cell to get from the grid
	 * @return GameObjectSet at that space.
	 */
	public GameObjectSet get(Position pos) {
		return grid[pos.getX()][pos.getY()];
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
	 * Helper function to check if a position is valid by seeing if the position falls within the bounds of the
	 * grid defined in the constants.
	 * @param pos The position to validate.
	 * @return true if valid, false if invalid.
	 */
	public boolean validatePos(Position pos) {
		return pos.getX() >= 0 && pos.getX() < Constants.GridColumns && pos.getY() >= 0 && pos.getY() < Constants.GridRows;
	}
}
