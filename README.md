# 🧩 Scalable Microservices-Based E-Commerce Platform

## 📖 Overview
A fully featured **microservices-based eCommerce platform** built with **Java Spring Boot** and deployed on **AWS Elastic Beanstalk**.  
The platform provides modules for **product management, authentication, payment integration**, and **event-driven notifications**, ensuring scalability, high performance, and reliability.

---

## ⚙️ Key Features
- **Microservices Architecture** – Independent services for product catalog, authentication, payment, and notifications.  
- **High Performance** – Optimized API response times from ~500 ms to ~50 ms using **Redis caching**, improving backend speed and scalability.  
- **Search & Discovery** – Advanced filtering and full-text search implemented with **Elasticsearch**, improving product discovery by **40%**.  
- **Event-Driven Email Service** – Sends thousands of emails simultaneously to enhance cross-service communication and responsiveness.  
- **Continuous Deployment** – Deployed on **AWS Elastic Beanstalk** with version control and CI/CD using **Git/GitHub**.  
- **API Testing** – Verified **RESTful API endpoints** with **Postman** to ensure reliable inter-service communication over HTTP.

---

## 🧠 Tech Stack
**Languages & Frameworks:** Java, Spring Boot  
**Architecture:** Microservices, RESTful APIs  
**Databases & Tools:** MySQL, Redis, Elasticsearch  
**Cloud & Deployment:** AWS Elastic Beanstalk  
**Version Control:** Git, GitHub  
**Testing Tools:** Postman  

---

## 🚀 How to Run Locally
```bash
# 1️⃣ Clone the repository
git clone https://github.com/shameershaik28/ProductService2025

# 2️⃣ Navigate into the project folder
cd ProductService2025

# 3️⃣ Build the project
mvn clean install

# 4️⃣ Run the application
mvn spring-boot:run
