import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceGenerator {
    public void printInvoice(Order order) {
        List<MarketplaceProduct> items = order.getItems();
        double subtotal = 0, taxTotal = 0, shippingTotal = 0;

        System.out.println("\\n================ MARKETPLACE TAX INVOICE ================");
        System.out.println("Invoice / Order ID : " + order.getOrderId());
        System.out.println("Customer           : " + order.getCustomerName());
        System.out.println("Date               : " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        System.out.println("Order status       : " + order.getStatus());
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-22s %5s %10s %10s %10s%n",
                "Product", "Qty", "Subtotal", "Tax", "Shipping");

        for (MarketplaceProduct item : items) {
            double lineSubtotal = item.getSubtotal();
            double tax = item.calculateFinalTax();
            double shipping = item.calculateShippingCost();
            subtotal += lineSubtotal;
            taxTotal += tax;
            shippingTotal += shipping;
            System.out.printf("%-22s %5d %10.2f %10.2f %10.2f%n",
                    shorten(item.getTitle(), 22), item.getQuantity(),
                    lineSubtotal, tax, shipping);
            if (item instanceof Shippable) {
                System.out.println("   Tracking: " + ((Shippable) item).generateTrackingId());
            } else {
                System.out.println("   Fulfillment: " + item.getFulfillmentType());
            }
            System.out.println("   Vendor: " + item.getVendorName()
                    + " | SKU: " + item.getSku());
        }

        double total = subtotal + taxTotal + shippingTotal;
        System.out.println("---------------------------------------------------------");
            System.out.printf("Subtotal: INR %.2f%n", subtotal);
            System.out.printf("Total tax: INR %.2f%n", taxTotal);  
            System.out.printf("Shipping: INR %.2f%n", shippingTotal);
            System.out.printf("GRAND TOTAL: INR %.2f%n", total);
            System.out.println("==============================================");
    }

    private String shorten(String value, int max) {
        return value.length() <= max ? value : value.substring(0, max - 3) + "...";
    }
}