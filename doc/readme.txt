CS141 Final Project: Team Broncodes

Team Members:
Nick Huiting
Jose Rodriguez
Michael Ackerman
Thanh Doan
Tenzin Tashitsang
Dennis Jimenez

Problem Description:
This project is a turn-based game that satisfies these criteria:
-The player, six ninjas, one suitcase and three power-ups are spawn in a 9x9 building
-The room is dark, the player has a one-tile-radius vision
-The player has 3 lives.
-The player can shoot or move each turn as well as look once every turn.
-The player is able to use three available power ups in the building (Ammo, Invincibility,  Radar).
-Have the enemies randomly move one space after each player turn.
-The player can kill an enemy if he/she has ammo and shoots toward the enemy. 
-An enemy can kill the player when adjacent to the player.
-The building has 9 rooms, one of which has the briefcase.
-Running out of lives means you lose, finding and retrieving the briefcase means you win.

Approach to Solution:
    The goal of this project was to create a robust yet expandable architecture that would allow for the possibility of multiple user interfaces and easily permit additions of other types of game objects and mechanics.  To that end, the game engine was loosely coupled from the user interface.  The game engine was passed an interface with minimal methods so that all input processing, game logic, and state changes would be done at the engine level.  The UI would be tasked with prompting the user for input and displaying game data upon the engine’s request.  This way, both a text-based and graphical user interface would be made possible.
    Another approach to make the program expandable was to implement an inheritance structure to allow for new types of game objects, power ups, or other items to be added to the game very easily.  All objects in the game inherit a superclass, which declares abstract methods and holds general data pertinent to all objects in play.  The Grid and sets of objects within each cell would only need to operate with respect to this superclass, and all descendent classes would be processed at the engine level, further consolidating game logic and increasing the reusability of the container objects.  Through this design, adding a new type of power up, for instance would be trivial, and allow for faster development.
    Additionally, to add a level of order to the game processing the engine was implemented as finite state machine.  This meant that processing input would be state-dependent, simplifying the debug process and ensuring that if a fringe case occurred, the game would never enter an unknown state.  This also allowed for more readable code, as processing by state naturally separated code into methods.  Additionally, this allows for a more expandable game because introducing a new game mode or user option would only involve implementing a new state.
    We also created a class to hold game constants and used those values in processing in the engine.  This helped to make the code more manageable and let us easily change values if necessary.  This would make implementing a difficulty system trivial.

Conclusion/Lessons Learned:
	Over the course of this project we learned many things.  We discovered communication is vital to creating a successful product and the more we communicate, the smoother the development process goes.  We also learned that code organization using subpackages is very important, and helps reduce the chaos of too many classes in a single level.  Additionally, the use of enums to keep track of states proved to be very useful and made the code very readable.  Another thing we learned is to create code that is expandable and reusable, as that will allow for more succinct code and better organization of the code itself among files.  Lastly, we learned the value of testing and writing code specifically for debugging in order to make the testing easier and more effective.