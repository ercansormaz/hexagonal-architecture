# 🧩 Hexagonal Architecture with Spring Boot — Calculator Example

## 📘 Overview

This project demonstrates the **Hexagonal Architecture (also known as Ports and Adapters
Architecture)** using **Spring Boot** through a simple yet educational example — a calculator that
performs basic arithmetic operations (**addition**, **subtraction**, **multiplication**, **division**)
and logs each calculation to a database.

The main goal of this project is **not** the business logic itself, but rather to show **how to
design clean, maintainable, and testable software** by isolating the core domain logic from
frameworks, external dependencies, and I/O operations.

---

## 🧱 Project Structure

The project is organized as a **multi-module Maven project** to clearly separate architectural
layers and dependencies:

```
hexagonal-architecture
 ├── domain
 ├── application
 ├── infrastructure
 ├── rest-api-adapter
 └── soap-service-adapter
```

---

## ⚙️ Domain Module

The `domain` module represents the **core business logic** of the application.
It contains only **pure Java classes**, with **no framework dependencies**.

- `model`
    - `Calculation` — represents a mathematical operation.
    - `CalculationType` — enum for operation types (ADD, SUBTRACT, MULTIPLY, DIVIDE).
- `port.in`
    - `CalculateUseCase` — defines the input port for calculation use cases.
- `port.out`
    - `CalculationLogger` — defines the output port for persisting calculation logs.
- `service`
    - `CalculatorService` — performs arithmetic operations.

**✅ Key Point:**
The domain layer knows nothing about frameworks, databases, or APIs — it only defines *what* needs
to be done, not *how*.

---

## 🧩 Application Module

The `application` module implements the use cases defined in the domain layer.

- `usecase`
    - `CalculateUseCaseImpl` — implements the `CalculateUseCase` port and uses `CalculatorService`.

This module depends **only** on the `domain` module and orchestrates the interaction between domain
logic and external adapters.

---

## 🏗️ Infrastructure Module

The `infrastructure` module provides database access and persistence logic.

- `model`
    - `CalculationEntity` — database entity for calculations.
- `repository`
    - `CalculationRepository` — Spring Data JPA repository.
- `adapter`
    - `CalculationLoggerJpaAdapter` — implements `CalculationLogger` port using JPA.

This module depends only on the **domain** module, adhering to the dependency rule:

> **Outer layers depend on inner layers, but not vice versa.**

---

## 🌐 REST API Adapter

The `rest-api-adapter` module exposes RESTful endpoints for performing calculations.

- `dto`
    - `CalculationRequest` and `CalculationResponse` — request/response DTOs.
- `config`
    - `BeanConfig` — registers `CalculatorService` and `CalculateUseCaseImpl` as Spring
      beans.
- `controller`
    - `CalculatorRestController` — provides endpoints for the four basic operations.

This module depends on `application` and `infrastructure` modules.

---

## 🧼 SOAP Service Adapter

The `soap-service-adapter` module exposes the same functionality as SOAP web services.

- `dto`
    - `CalculationRequest`, `CalculationResponse`
- `config`
    - `BeanConfig` — defines beans for domain/application services.
- `service`
    - `CalculatorSoapService` — SOAP endpoint definition.

Like the REST adapter, it depends on `application` and `infrastructure` modules.

--- 

## 💡 Design Highlights

- ✅ **Pure Java Domain**: No Spring or persistence dependencies in core logic.
- ✅ **Multi-module structure**: Clearly separates concerns for better readability and understanding.
- ✅ **Framework-agnostic domain layer**: Easy to replace REST with SOAP or gRPC without changing
  core logic.
- ✅ **Testability**: Domain and application logic can be unit-tested independently from the
  infrastructure.
- ✅ **Educational purpose**: The project intentionally uses a simple use case (calculator) to focus
  on architecture clarity.

---

## 🧠 Key Takeaways

Hexagonal Architecture encourages you to:

- Design your system **from the inside out** (domain first).
- Treat frameworks and tools as **details**, not as the foundation.
- Achieve **independent**, **pluggable adapters** (REST, SOAP, DB, CLI, etc.).
- Make your application **resilient to change** — whether it’s a new protocol, a different database,
  or a new interface.

---

## 🚀 Running the Project

To run the REST adapter:

```bash
cd rest-api-adapter
mvn spring-boot:run
```

To run the SOAP adapter:

```bash
cd soap-service-adapter
mvn spring-boot:run
```

Both adapters use the same domain and application logic, showcasing the **plug-and-play** nature of
Hexagonal Architecture.

---

## 🔹 Usage Examples

### REST API

**Addition**
```bash
curl --location 'http://localhost:8080/rest/calculator/add' \
--header 'Content-Type: application/json' \
--data '{
  "operand1": 10,
  "operand2": 5
}'
```
**Response**
```json
{
  "result": 15.0
}
```

### SOAP Service

**Addition**
```bash
curl --location 'http://localhost:8080/SOAP/CalculatorService' \
--header 'Content-Type: text/xml' \
--data '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cal="https://ercan.dev/poc/hexagonal-architecture/calculator">
  <soapenv:Header/>
  <soapenv:Body>
    <cal:add>
      <request>
        <operand1>10</operand1>
        <operand2>5</operand2>
      </request>
    </cal:add>
  </soapenv:Body>
</soapenv:Envelope>'
```
**Response**
```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
  <S:Body>
    <ns2:addResponse xmlns:ns2="https://ercan.dev/poc/hexagonal-architecture/calculator">
      <response>
        <result>15.0</result>
      </response>
    </ns2:addResponse>
  </S:Body>
</S:Envelope>
```

---

## 🧩 Conclusion

This project serves as a **hands-on example** for understanding Hexagonal Architecture with **Spring
Boot**.  
It demonstrates how clean modularization allows multiple interfaces (REST, SOAP) to coexist while
keeping the domain logic independent and reusable.

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repo, submit pull requests or open issues.

---

## 📜 License

This project is licensed under the MIT License.
