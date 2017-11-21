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
package edu.cpp.cs.cs141.prog_assgmnt_3.GameObjects;

/**
 * @author Nick Huiting
 * A class that used the enum to have the different visibility states of agents and items on the grid.
 */
public enum VisibilityPriority {
	None(0),
	Elevated(1),
	AlwaysRender(2);

	/**
	 * The level of priority using their integer associations.
	 */
    private int level;

    /**
     * Default constructor.
     * @param level as an integer value that is associated to the VisibiblePriority.
     */
    VisibilityPriority(int level) {
        this.level = level;
    }

    /**
     * Getter method for the level.
     * @return the level priority as an integer value.
     */
    public int getLevel() {
        return level;
    }
}
