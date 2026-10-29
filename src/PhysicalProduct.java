import java.util.UUID;

/** Physical items require packaging and delivery. */
public class PhysicalProduct extends MarketplaceProduct implements Shippable {
    private final double weightKg;
    private final double gstRate;

    public PhysicalProduct(String sku, String title, String vendorName,
                           double vendorPrice, int quantity,
                           double weightKg, double gstRate) {
        super(sku, title, vendorName, vendorPrice, quantity); // constructor chaining
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }
        if (gstRate < 0) {
            throw new IllegalArgumentException("GST rate cannot be negative.");
        }
        this.weightKg = weightKg;
        this.gstRate = gstRate;
    }

    public double getWeightKg() { return weightKg; }

    @Override
    public double calculateFinalTax() {
        return getSubtotal() * gstRate / 100.0;
    }

    @Override
    public double calculateShippingCost() {
        // Simple academic rule: ₹50 base + ₹20 per kg, multiplied by quantity.
        return (50.0 + 20.0 * weightKg) * getQuantity();
    }

    @Override
    public String generateTrackingId() {
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public String getFulfillmentType() {
        return "Physical shipment";
    }
}