import java.util.HashMap;
import java.util.Map;

/** Shared stock store. synchronized prevents two threads overselling the same SKU. */
public class Inventory {
    private final Map<String, Integer> stock = new HashMap<>();

    public synchronized void addStock(String sku, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Stock quantity must be positive.");
        }
        stock.put(sku, stock.getOrDefault(sku, 0) + quantity);
    }

    public synchronized void reserveStock(String sku, int quantity)
            throws InvalidQuantityException, OutOfStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Order quantity must be positive.");
        }
        int available = stock.getOrDefault(sku, 0);
        if (available < quantity) {
            throw new OutOfStockException("Not enough stock for SKU " + sku
                    + ". Available: " + available + ", requested: " + quantity);
        }
        stock.put(sku, available - quantity);
    }

    public synchronized int getAvailableStock(String sku) {
        return stock.getOrDefault(sku, 0);
    }
}