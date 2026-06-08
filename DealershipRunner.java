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

        System.out.println("Loading files:\n customers=" + customersFile + "\n inventory=" + inventoryFile + "\n transactions=" + transactionsFile + "\n");

        DealershipSystem ds = new DealershipSystem(customersFile, inventoryFile, transactionsFile);

        // Launch GUI with the loaded dealership system
        javax.swing.SwingUtilities.invokeLater(() -> new DealershipGUI(ds));
    }
}
