public class Main {
    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory();

        // Seed inventory for the demonstration.
        try {
            inventory.addStock("MOUSE-101", 10);
            inventory.addStock("WIN11-KEY", 20);
            inventory.addStock("FOOD-APPLE", 8);
        } catch (InvalidQuantityException ex) {
            System.out.println("Could not initialize stock: " + ex.getMessage());
            return;
        }

        Order order = new Order("Uday S");
        order.addItem(new PhysicalProduct(
                "MOUSE-101", "Wireless Mouse", "TechVendor", 1200.00, 1, 0.5, 18.0));
        order.addItem(new DigitalLicense(
                "WIN11-KEY", "Windows 11 Key", "SoftwareVendor", 4500.00, 1, 18.0));
        order.addItem(new PerishableFoodItem(
                "FOOD-APPLE", "Fresh Apples", "GreenFarm", 200.00, 2, 5.0, true));

        // A worker thread simulates asynchronous order fulfillment.
        Thread fulfillmentThread = new Thread(
                new OrderFulfillmentTask(order, inventory), "Fulfillment-1");
        fulfillmentThread.start();
        fulfillmentThread.join(); // wait until fulfillment status is final

        new InvoiceGenerator().printInvoice(order);

        System.out.println("Remaining stock for MOUSE-101: "
                + inventory.getAvailableStock("MOUSE-101"));

        // Demonstrate a domain exception for an unavailable item.
        Order secondOrder = new Order("Demo Customer");
        secondOrder.addItem(new PhysicalProduct(
                "MOUSE-101", "Wireless Mouse", "TechVendor", 1200.00, 999, 0.5, 18.0));
        Thread secondThread = new Thread(
                new OrderFulfillmentTask(secondOrder, inventory), "Fulfillment-2");
        secondThread.start();
        secondThread.join();
        System.out.println("Second order status: " + secondOrder.getStatus());
    }
}