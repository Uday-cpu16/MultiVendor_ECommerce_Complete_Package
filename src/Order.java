import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final String customerName;
    private final List<MarketplaceProduct> items = new ArrayList<>();
    private String status = "CREATED";

    public Order(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customerName = customerName;
    }

    public void addItem(MarketplaceProduct product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        items.add(product);
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public List<MarketplaceProduct> getItems() {
        return Collections.unmodifiableList(items);
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}