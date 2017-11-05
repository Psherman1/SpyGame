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

import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObject;
import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.GameObjectSet;

public class Grid {
	private GameObjectSet[][] grid = new GameObjectSet[Constants.GridColumns][Constants.GridRows];
	
	public Grid() {
		for (int i = 0; i < Constants.GridColumns; i++) {
			for (int j = 0; j < Constants.GridRows; j++) { 
				grid[i][j] = new GameObjectSet();
			}
		}
	}
	
	public boolean hasObjectAt(int x, int y) {
		if (validatePos(new Position(x, y)) == false)
			return false;
		
		return grid[x][y] != null;
	}
	
	public String getBoardString() throws Exception {
		//TODO we need to create a string representation of the board
		throw new Exception();
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
	 * Returns all positions in specified row. To be used for shooting and sight.
	 * @param row Integer of row desired.
	 * @return sorted array of all valid positions in specified row.
	 */
	public Position[] getRow(int row) {
		return null;
	}

	/**
	 * Returns all positions in specified col. To be used for shooting and sight.
	 * @param col Integer of col desired.
	 * @return sorted array of all valid positions in specified col.
	 */
	public Position[] getCol(int col) {
		return null;
	}

	/**
	 * Searches the grid for the specified object. Returns a position.
	 */
	public Position Search(GameObject obj) {
		// implement me.
		return null;
	}

	/**
	 * Helper function to get valid adjacent indexes from the grid.
	 * @param pos Position of the index to get the valid neighbors of.
	 * @return Returns an array of valid positions in the form of [pos1, pos2]
	 */
	public Position[] getAdjacent(Position pos) {
		return null;
	}

	/**
	 * Function to move an agent to a specific location. Updates the grid by removing the old location,
	 * and updating the index. This function should be called whenever ActiveAgent.move() is called.
	 * @param agent
	 * @param pos
	 */
	public void move(ActiveAgent agent, Position pos) {
		// TODO: exception if not valid.
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
