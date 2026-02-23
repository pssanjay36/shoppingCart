# Dynamic Discount Engine

## Overview
This project implements a **Dynamic Discount Engine** for an e-commerce platform. It leverages **gRPC** for high-performance, strongly-typed communication and the **Drools Rule Engine** for externalized, flexible discount logic. The system receives order details via gRPC, applies business discount rules defined in external `.drl` files, and returns the final calculated pricing details.

## Features
- **gRPC Integration:** Fast and efficient remote procedure calls between the client and server.
- **Drools Rule Engine:** Externalized business logic allowing easy updates to discount rules without requiring application recompilation.
- **Dynamic Pricing:** Calculates final order responses with original amounts, discount amounts, final amounts, and the specific rules applied.

## Architecture & Workflow

The workflow of the application is straightforward and highly decoupled:

1. **Client Request:** The client sends an `OrderRequest` to the server via gRPC.
2. **Fact Conversion:** The server receives the request and converts it into a Drools fact (an `Order` object).
3. **Rule Execution:** The Drools Engine evaluates the facts against the predefined `.drl` rules. Current sample rules include:
   - `orderAmount > 1000` → **10% discount**
   - `orderAmount > 5000` → **20% discount**
   - `customerType = PREMIUM` → **Extra 5% discount**
   - `orderAmount > 10000` AND `PREMIUM` → **30% flat discount**
4. **Response Construction:** The server builds an `OrderResponse` containing:
   - Original Amount
   - Discount Amount
   - Final Amount
   - Applied Rule Name
5. **Client Response:** The final calculated `OrderResponse` is sent back to the client.

### System Flow

```text
  [ Client (gRPC Stub) ]
            |
            | (OrderRequest)
            v
  +--------------------+
  |    gRPC Server     |
  +--------------------+
            |
            | (Order Object Fact)
            v
  +--------------------+
  |   Drools Engine    |
  +--------------------+
            |
            | (Evaluated Fact)
            v
  +--------------------+
  |    gRPC Server     |
  +--------------------+
            |
            | (OrderResponse)
            v
       [ Client ]
```

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.x

### Running the Application

**1. Build the project:**
Compile the source code and build the executable JAR:
```bash
mvn clean install
```

**2. Start the Server:**
Run the packaged JAR file to start the gRPC server and initialize the Drools engine:
```bash
java -jar target/discount-engine.jar
```

**3. Test via Postman (gRPC):**
You can easily test the gRPC endpoints using Postman:
1. Open Postman and click **New > gRPC Request**.
2. Enter the server address (e.g., `localhost:9090` - adjust the port as needed).
3. Go to the **Service definition** tab and import the project's `.proto` file.
4. Select the desired method from the dropdown menu.
5. In the **Message** tab, provide the `OrderRequest` payload in JSON format.
6. Click **Invoke** to send the message and view the returned `OrderResponse` showing the applied discounts.
