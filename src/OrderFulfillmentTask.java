public class OrderFulfillmentTask implements Runnable {
    private final Order order;
    private final Inventory inventory;

    public OrderFulfillmentTask(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            // Reserve each SKU. Inventory's synchronized method prevents overselling.
            for (MarketplaceProduct item : order.getItems()) {
                inventory.reserveStock(item.getSku(), item.getQuantity());
            }
            order.setStatus("CONFIRMED / READY FOR FULFILLMENT");
            System.out.println(Thread.currentThread().getName()
                    + ": order " + order.getOrderId() + " confirmed.");
        } catch (InvalidQuantityException | OutOfStockException ex) {
            order.setStatus("REJECTED");
            System.out.println(Thread.currentThread().getName()
                    + ": order " + order.getOrderId() + " rejected: " + ex.getMessage());
        }
    }
}