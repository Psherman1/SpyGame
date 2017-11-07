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
package edu.cpp.cs.cs141.prog_assgmnt_3;

import edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects.VisibilityPriority;

/**
 * Contains static utility methods.
 */
public final class Utilities {
	/**
	 * Returns whether p1 is of a greater priority than p2.  This is in lieu of the lack of operator overloads.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean priorityGreater(VisibilityPriority p1, VisibilityPriority p2) {
		return p1.getLevel() > p2.getLevel();
	}
}
