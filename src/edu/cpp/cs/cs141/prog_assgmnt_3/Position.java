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
 * Immutable position that includes an x and a y value. 
 */
public final class Position {
	private final int x;
	private final int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public Position changeX(int x) {
		return new Position(x, this.y);
	}
	
	/**
	 * 
	 * @param y
	 * @return
	 */
	public Position changeY(int y) {
		return new Position(this.x, y);
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public boolean isAdjacent(int x, int y) {
		return 
			posEquals(x - 1, y) || 
			posEquals(x + 1, y) ||
			posEquals(x, y - 1) || 
			posEquals(x, y + 1);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public boolean posEquals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public boolean posEquals(Position pos) {
		return this.x == pos.getX() && this.y == pos.getY();
	}
}
