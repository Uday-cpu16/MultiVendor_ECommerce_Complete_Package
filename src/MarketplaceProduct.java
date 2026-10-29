import java.util.Objects;

/**
 * Common parent for every product sold on the marketplace.
 * Private fields protect product state (encapsulation).
 */
public abstract class MarketplaceProduct {
    private final String sku;
    private final String title;
    private final String vendorName;
    private final double vendorPrice;
    private final int quantity;

    public MarketplaceProduct(String sku, String title, String vendorName,
                              double vendorPrice, int quantity) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be empty.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (vendorName == null || vendorName.isBlank()) {
            throw new IllegalArgumentException("Vendor name cannot be empty.");
        }
        if (vendorPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.sku = sku;
        this.title = title;
        this.vendorName = vendorName;
        this.vendorPrice = vendorPrice;
        this.quantity = quantity;
    }

    public String getSku() { return sku; }
    public String getTitle() { return title; }
    public String getVendorName() { return vendorName; }
    public double getVendorPrice() { return vendorPrice; }
    public int getQuantity() { return quantity; }

    public double getSubtotal() {
        return vendorPrice * quantity;
    }

    /** Each concrete product class supplies its own tax rule. */
    public abstract double calculateFinalTax();

    /** Products with physical delivery override this method. */
    public double calculateShippingCost() {
        return 0.0;
    }

    public String generateTrackingId() {
        return "DIGITAL-" + sku;
    }

    public String getFulfillmentType() {
        return "Standard";
    }
}