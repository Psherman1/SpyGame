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
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

import java.io.Serializable;

import edu.cpp.cs.cs141.prog_assgmnt_3.Utilities;

/**
 * @author Nick Huiting
 * Class to hold game objects.  Can hold 0, 1, or 2 objects.
 */
public class GameObjectSet implements Serializable  {
	private GameObject[] objects = new GameObject[2];
	private int count = 0;
	
	/**
	 * Creates a new set with one object.
	 */
	public GameObjectSet(GameObject obj) {
		add(obj);
	}
	
	/**
	 * Creates an empty game object set.
	 */
	public GameObjectSet() {
		
	}
	
	/**
	 * Gets the number of objects stored in the set.
	 * @return count. 
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Gets an object at the specified index.
	 * @param index place at which you want to access
	 * @return The object if it can be accessed, null otherwise.
	 */
	public GameObject getAt(int index) {
		if (count == 0)
			return null;
		
		if (count == 1 && index == 0)
			return objects[0];
		
		return index < 2 ? objects[index] : null;
	}
	
	/**
	 * Adds an object to the set of objects.
	 * @param obj from the GameObject Class. 
	 * @throws IndexOutOfBoundsException if the set is full.
	 */
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
	 * The symbol returned is dependent on the priority levels of the objects it contains.
	 * @return the char symbol of the objects on the grid; '*','B','I','P','X','@','A', ' ', etc.
	 */
	public char getSymbol(boolean isVisible, boolean debug, boolean radar) {
		if (count == 0)
			return debug || isVisible ? ' ' : '*';
		
		if (count == 1)
			return debug || (isVisible || objects[0].getPriority() == VisibilityPriority.AlwaysRender) ? objects[0].getSymbol(debug, radar) : '*';
		
		if (isVisible == false) {
			if (objects[1].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[1].getSymbol(debug, radar);
			
			if (objects[0].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[0].getSymbol(debug, radar);
			
			if (debug == false)
				return '*';
		}
		
		return Utilities.priorityGreater(objects[0].getPriority(), objects[1].getPriority()) ? 
				objects[0].getSymbol(debug, radar) : 
				objects[1].getSymbol(debug, radar);
	}
}
