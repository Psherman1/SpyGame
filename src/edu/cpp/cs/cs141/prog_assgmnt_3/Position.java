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

import java.io.Serializable;

/**
 * Immutable position class that includes an x and a y value.
 */
public final class Position implements Serializable {
	private final int x;
	private final int y;

	/**
	 *
	 * @param x x value of the position
	 * @param y y value of the position
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for the X value.
	 * @return x value of the position.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for the Y value.
	 * @return y value for the position.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Creates a new position with the new specified x value, but same y value. No setters because this class
	 * is immutable.
	 * @param x new x value for the new position
	 * @return new position with the new x value, but the same y value.
	 */
	public Position changeX(int x) {
		return new Position(x, this.y);
	}

	/**
	 * Creates a new position with the new specified y value, but same x value. No setters because this class
	 * is immutable.
	 * @param y new y value for the new position
	 * @return new position with the new y value, but the same x value.
	 */
	public Position changeY(int y) {
		return new Position(this.x, y);
	}
	
	/**
	 * Helper method to check whether the specified x and y value is adjacent to this position. Only values
	 * directly up, down, left, or right are considered adjacent. Diagonals are not considered.
	 * @param x x value to check adjacency
	 * @param y y value to check adjacency
	 * @return true if adjacent, otherwise false.
	 */
	public boolean isAdjacent(int x, int y) {
		return 
			posEquals(x - 1, y) || 
			posEquals(x + 1, y) ||
			posEquals(x, y - 1) || 
			posEquals(x, y + 1);
	}
	
	/**
	 * Converts the Position into its string representation.
	 * @return String representation of the position.
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Helper method to compare a position with 2 separate integer values x and y.
	 * @param x x value to compare to
	 * @param y y value to compare to
	 * @return true if the two arguments are equivalent to the position, otherwise false.
	 */
	public boolean posEquals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	/**
	 * Helper method to compare two positions
	 * @param pos other position to compare to
	 * @return true if the two positions are equal, otherwise false.
	 */
	public boolean posEquals(Position pos) {
		return this.x == pos.getX() && this.y == pos.getY();
	}
}
