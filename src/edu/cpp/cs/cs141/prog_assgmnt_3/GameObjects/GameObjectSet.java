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
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

/**
 *
 */
public class GameObjectSet {
	private GameObject[] objects = new GameObject[2];
	private int count = 0;
	
	public void add(GameObject obj) throws IndexOutOfBoundsException {
		if (count < 2) {
			objects[count] = obj;
			count++;
		}
		
		throw new IndexOutOfBoundsException("Set is full.");
	}
	
	/**
	 * Gets the symbol for the set in the space.
	 * @return
	 */
	public char getSymbol() {
		if (count == 0)
			return ' ';
		
		if (count == 1)
			return objects[0].getSymbol();
		
		return objects[0].getPriority() > objects[1].getPriority() ? objects[0].getSymbol() : objects[1].getSymbol();
	}
}
