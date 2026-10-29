# Multi-Vendor E-Commerce Order Fulfillment & Invoice Generator
CMR University — OOP Using Java & Kotlin (4CTGC2011), Project 25

## Requirements
- JDK 11 or newer
- Terminal / command prompt

## Compile and run
From this folder:
```bash
javac -d out src/*.java
java -cp out Main
```

## Features
- `Shippable` interface for shipping cost and tracking IDs.
- `MarketplaceProduct` abstract class with private fields and abstract `calculateFinalTax()`.
- `PhysicalProduct`, `DigitalLicense`, and `PerishableFoodItem`.
- Constructor chaining via `super(...)`.
- Polymorphic tax, shipping and fulfillment behavior.
- `Inventory` uses synchronized methods to prevent overselling.
- Custom checked exceptions: `InvalidQuantityException`, `OutOfStockException`.
- `OrderFulfillmentTask` implements `Runnable`; the demo starts a background thread.
- Invoice includes subtotal, tax, shipping, total, vendor, SKU, and tracking/fulfillment details.

## Academic assumptions
GST rates are configurable demonstration inputs (not tax advice or a statement of current legal rates).
Shipping prices are simplified academic rules. The example mouse uses ₹1,200 + 18% tax and ₹50 + ₹20/kg shipping; the digital license uses ₹4,500 + 18% tax and zero shipping. Perishable food uses configurable tax and optional chilled packaging.

## File map
- `src/` — executable Java source files
- `report.pdf` — technical report
- `report.docx` — editable report
- `UML_Class_Diagram.puml` — PlantUML source
- `UML_Class_Diagram.txt` — text class diagram
- `Viva_Questions_and_Answers.md` — viva preparation
