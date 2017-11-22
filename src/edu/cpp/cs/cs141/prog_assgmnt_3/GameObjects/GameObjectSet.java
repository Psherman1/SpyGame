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

import java.io.Serializable;

import edu.cpp.cs.cs141.prog_assgmnt_3.Utilities;

/**
 * @author Nick Huiting
 * Class to set the game objects as an array.
 */
public class GameObjectSet implements Serializable  {
	private GameObject[] objects = new GameObject[2];
	private int count = 0;
	
	//Default constructor that takes a Gameobject as an argument and adds it to the array objects.
	public GameObjectSet(GameObject obj) {
		add(obj);
	}
	
	// Default constructor.
	public GameObjectSet() {
		
	}
	
	/**
	 * Gets the counts of the array.
	 * @return count as an integer type. 
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Gets index of the array.
	 * @param index place at which you want to access
	 * @return array if it can be accessed.
	 */
	public GameObject getAt(int index) {
		if (count == 0)
			return null;
		
		if (count == 1 && index == 0)
			return objects[0];
		
		return index < 2 ? objects[index] : null;
	}
	
	/**
	 * Adds an object to the array of the object array.
	 * @param obj from the GameObject Class that contains; postion, visibility, and symbol. 
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
	 * @return the char symbol of the objects on the grid; '*','B','I','P','X','@','A', ' ', etc.
	 */
	public char getSymbol(boolean isVisible, boolean debug, boolean radar) {
		if (count == 0)
			return debug || isVisible ? ' ' : '*';
		
		if (count == 1)
			return debug || (isVisible || objects[0].getPriority() == VisibilityPriority.AlwaysRender) ? objects[0].getSymbol(debug, radar) : '*';
		
		
		if (isVisible == false) {
			if (objects[0].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[0].getSymbol(debug, radar);
			
			if (objects[1].getPriority() == VisibilityPriority.AlwaysRender)
				return objects[1].getSymbol(debug, radar);
			
			if (debug == false)
				return '*';
		}
		
		return Utilities.priorityGreater(objects[0].getPriority(), objects[1].getPriority()) ? 
				objects[0].getSymbol(debug, radar) : 
				objects[1].getSymbol(debug, radar);
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
