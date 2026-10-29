import java.util.UUID;

/** Perishable groceries use a separate configurable tax and chilled-packing fee. */
public class PerishableFoodItem extends MarketplaceProduct implements Shippable {
    private final double gstRate;
    private final boolean chilledPackagingRequired;

    public PerishableFoodItem(String sku, String title, String vendorName,
                              double vendorPrice, int quantity, double gstRate,
                              boolean chilledPackagingRequired) {
        super(sku, title, vendorName, vendorPrice, quantity); // constructor chaining
        if (gstRate < 0) {
            throw new IllegalArgumentException("GST rate cannot be negative.");
        }
        this.gstRate = gstRate;
        this.chilledPackagingRequired = chilledPackagingRequired;
    }

    @Override
    public double calculateFinalTax() {
        return getSubtotal() * gstRate / 100.0;
    }

    @Override
    public double calculateShippingCost() {
        double packaging = chilledPackagingRequired ? 35.0 : 10.0;
        return packaging * getQuantity();
    }

    @Override
    public String generateTrackingId() {
        return "FOOD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Override
    public String getFulfillmentType() {
        return chilledPackagingRequired ? "Perishable / chilled delivery" : "Perishable / standard delivery";
    }
}