import java.io.*;

/**
 * Application entry point that optionally loads custom data files and
 * launches the {@link DealershipGUI} on the Swing event dispatch thread.
 */
public class DealershipRunner {

    /**
     * Starts the dealership system and GUI.
     * Uses {@code test_customers.txt}, {@code test_inventory.txt}, and
     * {@code test_transactions.txt} by default, or three file paths
     * from command-line arguments when provided.
     *
     * @param arg optional array: [customersFile, inventoryFile, transactionsFile]
     */
    public static void main(String[] arg) {
        String customersFile = "test_customers.txt";
        String inventoryFile = "test_inventory.txt";
        String transactionsFile = "test_transactions.txt";
        if (arg != null && arg.length >= 3) {
            customersFile = arg[0];
            inventoryFile = arg[1];
            transactionsFile = arg[2];
        }

    // Create the system (GUI will create its own by default). We avoid printing to terminal.
    DealershipSystem ds = new DealershipSystem(customersFile, inventoryFile, transactionsFile);

    // Launch GUI on the Event Dispatch Thread
    javax.swing.SwingUtilities.invokeLater(() -> new DealershipGUI());
    }
}
