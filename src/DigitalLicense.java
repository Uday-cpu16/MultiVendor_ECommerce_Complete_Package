/** Digital products are delivered electronically and have no shipping fee. */
public class DigitalLicense extends MarketplaceProduct {
    private final double gstRate;

    public DigitalLicense(String sku, String title, String vendorName,
                          double vendorPrice, int quantity, double gstRate) {
        super(sku, title, vendorName, vendorPrice, quantity); // constructor chaining
        if (gstRate < 0) {
            throw new IllegalArgumentException("GST rate cannot be negative.");
        }
        this.gstRate = gstRate;
    }

    @Override
    public double calculateFinalTax() {
        return getSubtotal() * gstRate / 100.0;
    }

    @Override
    public String getFulfillmentType() {
        return "Email / digital delivery";
    }
}