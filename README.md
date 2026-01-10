# 🚀 Kafka Microservices Demo (Spring Boot + Kafka 4.x)

This repository demonstrates an **end-to-end, event-driven microservices architecture** using **Apache Kafka (KRaft mode)** and **Spring Boot**.

The project is designed for **hands-on learning**, **interview preparation**, and **real-world Kafka microservices understanding**.

---

## 🧩 Business Use Case

**Order Processing System** implemented using **Kafka as the event backbone**.

The system processes an order asynchronously through multiple microservices without direct REST communication between them.

---

## 🏗️ Architecture Overview

```
Client
  |
  v
Order Service (REST + Kafka Producer)
  |
  v
Kafka Topic: order-created
  |        |        |
  v        v        v
Payment  Inventory  Notification
Service  Service    Service
  |
  v
Kafka Topic: payment-completed
  |
  v
Shipping Service
  |
  v
Kafka Topic: order-shipped
  |
  v
Notification Service
```

### 🔑 Key Architecture Principles

* Event-driven (no synchronous inter-service REST calls)
* Loose coupling
* Horizontal scalability
* Eventual consistency (Saga – choreography)
* Kafka KRaft (no ZooKeeper)

---

## 🧱 Microservices in This Repository

| Service              | Port | Role                      |
| -------------------- | ---- | ------------------------- |
| order-service        | 8081 | REST API + Kafka Producer |
| payment-service      | 8082 | Kafka Consumer → Producer |
| inventory-service    | 8083 | Kafka Consumer → Producer |
| shipping-service     | 8084 | Kafka Consumer → Producer |
| notification-service | 8085 | Kafka Consumer            |

---

## 📦 Kafka Topics

| Topic Name         | Purpose           |
| ------------------ | ----------------- |
| order-created      | New order placed  |
| payment-completed  | Payment processed |
| inventory-reserved | Stock reserved    |
| order-shipped      | Order shipped     |

All topics:

* Partitions: `3`
* Replication factor: `1` (local setup)

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Kafka
* Apache Kafka 4.x (KRaft mode)
* Maven

---

## ⚙️ Prerequisites

* Java 17+
* Maven
* Apache Kafka 4.x running locally
* IDE (IntelliJ IDEA / VS Code)

Verify Kafka:

```bash
kafka-topics.sh --bootstrap-server localhost:9092 --list
```

---

## 🚀 Getting Started

### 1️⃣ Start Kafka (KRaft Mode)

```bash
kafka-server-start.sh config/server.properties
```

---

### 2️⃣ Create Kafka Topics

```bash
kafka-topics.sh --create --topic order-created --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092
kafka-topics.sh --create --topic payment-completed --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092
kafka-topics.sh --create --topic inventory-reserved --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092
kafka-topics.sh --create --topic order-shipped --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092
```

---

### 3️⃣ Run the Microservices

Start services in the following order:

1. `payment-service`
2. `inventory-service`
3. `shipping-service`
4. `notification-service`
5. `order-service`

> 💡 Consumers should always start before producers

---

### 4️⃣ Test the Flow

Create an order:

```bash
curl -X POST "http://localhost:8081/orders?product=iPhone&quantity=1"
```

Expected logs across services:

```
Order created
Payment successful
Inventory reserved
Order shipped
Notification sent
```

---

## 📁 Project Structure

```
kafka-microservices-demo/
 ├── order-service
 ├── payment-service
 ├── inventory-service
 ├── shipping-service
 └── notification-service
```

Each service follows:

```
controller/   (only order-service)
consumer/
service/
event/
config/
```

---

## 📄 Event Design

* Immutable events
* JSON serialization
* Keyed by `orderId`
* Shared BaseEvent structure

Example event:

```json
{
  "eventId": "uuid",
  "eventType": "OrderCreated",
  "orderId": "123",
  "timestamp": "2026-01-06T10:00:00Z"
}
```

---

## 🔐 Reliability & Production Readiness

* Idempotent producers
* `acks=all`
* Consumer groups per service
* Partition-based ordering
* Structured logging

---

## 🧠 Learning Outcomes

✔ Kafka producers & consumers
✔ Event-driven microservices
✔ Saga choreography
✔ Kafka KRaft understanding
✔ Real-world system design
✔ Interview-ready Kafka architecture

---

## 🧪 Future Enhancements

* Dead Letter Topics (DLT)
* Retry topics
* Schema Registry (Avro)
* Docker Compose setup
* Distributed tracing
* Exactly-once semantics

---

## 👨‍💻 Author

**Janmajaya (Jay)**
Java Backend Developer | Kafka | Microservices | Cloud

---

## ⭐ Final Note

This project is intentionally designed to be **simple, realistic, and extensible**.

> Perfect for learning Kafka, building confidence, and showcasing real-world microservices design.

Happy learning 🚀
