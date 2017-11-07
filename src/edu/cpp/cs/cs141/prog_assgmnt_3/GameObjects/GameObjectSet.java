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

import edu.cpp.cs.cs141.prog_assgmnt_3.Utilities;

/**
 *
 */
public class GameObjectSet {
	private GameObject[] objects = new GameObject[2];
	private int count = 0;
	
	public GameObjectSet(GameObject obj) {
		add(obj);
	}
	
	public GameObjectSet() {
		
	}
	
	public void add(GameObject obj) throws IndexOutOfBoundsException {
		if (count < 2) {
			objects[count] = obj;
			count++;
			return;
		}
		
		throw new IndexOutOfBoundsException("Set is full.");
	}
	
	/**
	 * Removes the object from the set.
	 * @param obj
	 * @return Returns true if successful, false otherwise.
	 */
	public boolean remove(GameObject entry) {
		if (count == 0)
			return false;
		
		if (count == 1) {
			if (objects[0] == entry)
				objects[0] = null;
			
			count--;
			return true;
		}
		
		if (count == 2) {
			if (objects[0] == entry) {
				objects[0] = objects[1];
				objects[1] = null;
				count--;
				return true;
			}
			
			if (objects[1] == entry) {
				objects[1] = null;
				count--;
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	/**
	 * Gets the symbol for the set in the space.  If the set isn't adjacent, an asterisk is returned to represent an non-visible space.
	 * @return
	 */
	public char getSymbol(boolean isVisible, boolean debug) {
		if (count == 0)
			return debug || isVisible ? ' ' : '*';
		
		if (count == 1)
			return debug || (isVisible || objects[0].getPriority() == VisibilityPriority.AlwaysRender) ? objects[0].getSymbol() : '*';
		
		
		if (isVisible == false) {
			if (objects[0].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[0].getSymbol();
			
			if (objects[1].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[1].getSymbol();
			
			if (debug == false)
				return '*';
		}
		
		return Utilities.priorityGreater(objects[0].getPriority(), objects[1].getPriority()) ? 
				objects[0].getSymbol() : 
				objects[1].getSymbol();
	}

	/**
	 * Determines whether the object is contained within the set.
	 */
	public boolean search(GameObject entry) {
		
		if (count == 0)
			return false;
		
		if (count == 1) {
			return objects[0] == entry;
		}
		
		if (count == 2) {
			return objects[0] == entry || objects[1] == entry;
		}
		
		return false;
	}
}
