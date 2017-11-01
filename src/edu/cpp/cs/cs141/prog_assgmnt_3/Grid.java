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

/**
 * @author Thanh Doan
 *
 */
public class Grid {
	private GameObject[][] grid = new GameObject[9][9];
	
	public boolean hasObjectAt(int x, int y) {
		if (x < 0 || x > 9  || y < 0 || y > 9)
			return false;
		
		return grid[x][y] != null;
	}
	
	public String getBoardString() throws Exception {
		//TODO 
		throw new Exception();
	}
}
