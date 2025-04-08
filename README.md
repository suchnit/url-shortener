# URL Shortener

A high-performance, scalable URL shortener built with **Spring Boot**, **PostgreSQL**, and **Redis**. Designed with clean architecture and system design best practices in mind.

---

## 🚀 Features
- Generate unique short URLs for long links
- Support for custom aliases
- Expiry support for short links
- Redirection with caching (Redis)
- Access logs and basic analytics
- Pluggable rate limiting and security features

---

## 🧠 System Design Highlights
- **Hybrid ID + Base62** encoding for efficient short code generation
- **Cache Aside Pattern** with Redis for ultra-fast redirection
- **Async processing** ready (Kafka/RabbitMQ placeholders)
- **Daily rotated access logs** via embedded Tomcat

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.x
- PostgreSQL
- Redis
- Maven
- Docker (optional)

---

## 📦 Project Structure
