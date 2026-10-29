# Viva Questions and Answers

1. **What is the purpose of this project?**  
   To model a marketplace order containing products from multiple vendors, reserve stock, fulfill an order, and generate an itemized invoice.

2. **Why is `MarketplaceProduct` abstract?**  
   It represents common product data, while each product type supplies its own tax calculation.

3. **What is an interface?**  
   An interface defines a contract. `Shippable` requires shipping-cost and tracking-ID methods.

4. **Why does `DigitalLicense` not implement `Shippable`?**  
   It is delivered electronically, so the demo charges zero shipping and does not require a courier tracking number.

5. **What is constructor chaining?**  
   Calling a parent constructor with `super(...)` to initialize shared fields before initializing subclass-specific fields.

6. **Where is polymorphism used?**  
   `InvoiceGenerator` loops through `MarketplaceProduct` references and calls `calculateFinalTax()`; Java selects the subclass implementation at runtime.

7. **What is encapsulation?**  
   Fields are private and accessed through methods, keeping object state controlled.

8. **Why use `synchronized` in `Inventory`?**  
   It protects shared stock updates so two threads cannot reserve the same available units at the same time.

9. **What is a race condition?**  
   An incorrect result caused by concurrent threads accessing and changing shared state without adequate coordination.

10. **Why implement `Runnable`?**  
    It separates the fulfillment task from the `Thread` object and allows the task to execute on a worker thread.

11. **What is the role of `join()`?**  
    It makes the main thread wait for the fulfillment thread to finish before printing the final invoice status.

12. **Why use custom exceptions?**  
    They represent domain-specific errors such as invalid quantities and insufficient stock.

13. **How is the invoice total calculated?**  
    Grand total = sum of line subtotals + sum of product taxes + sum of shipping fees.

14. **How is shipping calculated for physical products?**  
    In this academic example: (₹50 base + ₹20 × weight in kg) × quantity.

15. **How is digital product shipping handled?**  
    The base product's shipping cost is zero and fulfillment is marked as email/digital delivery.

16. **Can the GST percentages be treated as official tax advice?**  
    No. They are configurable demonstration inputs from the academic example and should be checked against current applicable rules for real commerce.

17. **What happens when stock is insufficient?**  
    `OutOfStockException` is thrown, caught by the fulfillment task, and the order is marked rejected.

18. **What is an overridden method?**  
    A subclass provides its own implementation of a method declared in a superclass or interface.

19. **What is the use of `List<MarketplaceProduct>`?**  
    It lets one order store mixed product types under a common parent type.

20. **What are the limitations?**  
    This is a console simulation: no database, payment gateway, real email service, persistent inventory, or production tax engine.

21. **How could you improve it?**  
    Add database persistence, atomic all-or-nothing multi-item stock reservation, payment/refund workflows, vendor-wise shipment grouping, and unit tests.

22. **Why is inventory stock private?**  
    To prevent arbitrary code from modifying it without validation and synchronization.

23. **What does `@Override` do?**  
    It tells the compiler that a method is intended to override a parent or interface method and catches signature mistakes.

24. **What is the difference between subtotal and grand total?**  
    Subtotal is the item price before tax and shipping; grand total includes both.

25. **How does this map to Modules 1–3?**  
    Classes, constructors and access control (Module 1); abstraction, inheritance and polymorphism (Module 2); custom exceptions, threads and synchronization (Module 3).
