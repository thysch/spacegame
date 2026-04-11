//=======================================================================================
//
// Purpose: Handles strictly text output and formatting.
//
//=======================================================================================
package org.example.view;

public class GameView {
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";

    public GameView() {
    }

    //-----------------------------------------------------------------------------
    // Purpose: Color an item name based on what it is
    // Input  : string (item name)
    //-----------------------------------------------------------------------------
    public String colorItem(String itemName) {
        return switch (itemName.toLowerCase().trim()) {
            case "lamp"     -> YELLOW + itemName + RESET;
            case "key"      -> CYAN + itemName + RESET;
            case "sword"    -> RED + itemName + RESET;
            default         -> GREEN + itemName + RESET;
        };
    }


    //-----------------------------------------------------------------------------
    // Purpose: Game welcome message
    //-----------------------------------------------------------------------------
    public void showWelcome() {
        System.out.println("Welcome to game!!");
    }

    //-----------------------------------------------------------------------------
    // Purpose: Display available commands if the player types "help" || "h"
    //-----------------------------------------------------------------------------
    public void showHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("  north/south/east/west           - move in a direction");
        System.out.println("  go/move/walk <direction>        - move in a direction");
        System.out.println("  get/take/grab <item>            - pick up an item");
        System.out.println("  look (l)                        - look around");
        System.out.println("  undo (u)                        - undo last move (max 3)");
        System.out.println("  help (h)                        - show this help");
        System.out.println("  quit/exit (q)                   - quit the game");
        System.out.println("-".repeat(50));
    }


    //-----------------------------------------------------------------------------
    // Purpose: Just prints "> "
    //-----------------------------------------------------------------------------
    public void showPrompt() {
        System.out.print("> ");
    }

    //-----------------------------------------------------------------------------
    // Purpose: Print scene description & horizontal line
    // Input  : string (description)
    //-----------------------------------------------------------------------------
    public void render(String description) {
        System.out.println("\n" + description);
        System.out.println("-".repeat(50));
    }

    //-----------------------------------------------------------------------------
    // Purpose: Display message
    // Input  : string (message)
    //-----------------------------------------------------------------------------
    public void showMessage(String msg) {
        System.out.println("\n" + msg);
    }

    //-----------------------------------------------------------------------------
    // Purpose: Game over screen for win/lose
    // Input  : bool (victory)
    //-----------------------------------------------------------------------------
    public void showGameOver(boolean won) {
        if (won) {
            System.out.println("\nYou found the treasure! You win!");
        } else {
            System.out.println("\nGame over. Better luck next time.");
        }
    }
}
