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

public class Grid {
	private GameObject[][] grid = new GameObject[Constants.GridColumns][Constants.GridRows];
	
	public boolean hasObjectAt(int x, int y) {
		if (x < 0 || x > Constants.GridColumns || y < 0 || y > Constants.GridRows)
			return false;
		
		return grid[x][y] != null;
	}
	
	public String getBoardString() throws Exception {
		//TODO 
		throw new Exception();
	}

	/**
	 *
	 * @param pos position of an item to get from the grid
	 * @return GameObject of the item if found, otherwise null
	 */
	public GameObject get(Position pos) {
		return null;
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
	 * Searches the grid for the specified object. Returns a position [x,y].
	 */
	public int[] Search(GameObject obj) {
		// implement me.
		return null;
	}

	/**
	 * Helper function to get valid adjacent indexes from the grid.
	 * @param pos pos of the index to get the valid neighbors of.
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
	public void move(ActiveAgent agent, Position pos){
		// TODO: exception if not valid.
	}

	/**
	 * Helper function to check if a position is valid.
	 * @param pos
	 * @return
	 */
	public Boolean validatePos(Position pos) {
		return null;
	}
}
