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
