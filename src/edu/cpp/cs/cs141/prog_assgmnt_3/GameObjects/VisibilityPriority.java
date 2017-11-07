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
 *
 */
public enum VisibilityPriority {
	None(0),
	Elevated(1),
	AlwaysRender(2);

    private int level;

    VisibilityPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
