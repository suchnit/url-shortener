# URL Shortener

A high-performance, scalable URL shortener built with **Spring Boot**, **PostgreSQL**, and **Redis**. Designed with clean architecture and system design best practices in mind.

---

## 🚀 Features
- Generate unique short URLs for long links
- Support for custom aliases - TODO
- Expiry support for short links
- Redirection with caching (Redis) - TODO
- Access logs and basic analytics - TODO
- Pluggable rate limiting and security features - TODO

---

## 🧠 System Design Highlights
- **Hybrid ID + Base62** encoding for efficient short code generation
- **Cache Aside Pattern** with Redis for ultra-fast redirection - TODO
- **Async processing** ready (Kafka/RabbitMQ placeholders) - TODO
- **Daily rotated access logs** via embedded Tomcat - TODO

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.x
- PostgreSQL
- Redis - TODO
- Maven
- Docker (optional) - TODO

---

## 📦 Project Structure
