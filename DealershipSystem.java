public class DealershipSystem {
    private Customer[] customers;
    private Vehicle[] vehicles;
    private Transaction[] transactionHistory;
    private int numCusomer;
    private int numCars;
    private int numTransactions;

    public DealershipSystem(int numCustomer, int numCars, int numTransactions, String customerFileName, String inventoryFileName, String transactionFileName) {
        // I/O
    }

    public DealershipSystem() {
        // Prompt for any customers, vehicles, or history
    }

    public boolean acceptDeal(Customer customer, Account acc) {
        if(acc == null) {
            return false;
        } else if(!acc.validate(vehicles)) {
            return false;
        } else {
            int suggestedPrice = customer.getRandomizedDealPrice();
            if(acc instanceof SellerAccount) {
                return ((SellerAccount)acc).sellVehicle(suggestedPrice);
            } else if(acc instanceof BuyerAccount) {
                return ((BuyerAccount)acc).buyVehicle(suggestedPrice);
            }
            return false;
        }
    }

    public boolean acceptDeal(Customer customer, Account acc, Vehicle vehicle) {
        if(acc == null) {
            return false;
        } else if(!acc.validate(vehicles)) {
            return false;
        } else {
            return ((TradeInAccount)acc).tradeVehicle(vehicle);
        }
    }

    
}
