import java.util.Scanner;
public class UI{
  private GameEngine game = null;
	private Scanner keyboard = null;

	public UI(GameEngine game) {
		this.game = game;
		keyboard = new Scanner(System.in);
	}


}
