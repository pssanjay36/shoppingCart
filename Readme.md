===Overview
This project implements a Dynamic Discount Engine for an e‑commerce platform.
It uses gRPC for communication and Drools Rule Engine for externalized discount logic.
The system receives order details via gRPC, applies discount rules defined in .drl files, and returns the final pricing details.

===Architecture


1)Client sends an OrderRequest via gRPC.

2)Server receives the request and converts it into a Drools fact (Order object).

3)Drools Rule Engine executes rules from .drl files:

If orderAmount > 1000 → 10% discount

If orderAmount > 5000 → 20% discount

If customerType = PREMIUM → extra 5% discount

If orderAmount > 10000 AND PREMIUM → 30% flat discount

4)Server builds an OrderResponse with:

Original amount

Discount amount

Final amount

Applied rule

5) Response is sent back to the client via gRPC.

Client (gRPC Stub)
        |
        v
-------------------
|   gRPC Server   |
-------------------
        |
        v
-------------------
| Drools Engine   |
-------------------
        |
        v
Response (OrderResponse)


===How to Run

1. Build the project

mvn clean install

2. Start the server

java -jar target/discount-engine.jar

3. Run the client
Execute the sample client (TestClient.java) to send requests:


mvn exec:java -Dexec.mainClass="com.example.shoppingCart.service.TestClient"



