import java.io.*;

public class DealershipRunner {
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
