# 🏥 Hospital Management System

A production-ready Spring Boot backend application for managing hospital operations including patients, doctors, authentication, and role-based access control.

Built with secure authentication, Dockerized infrastructure, and PostgreSQL database support.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- OAuth2 (Google & GitHub)
- PostgreSQL
- Hibernate / JPA
- Docker & Docker Compose
- Maven

---

## 🏗 Features

- JWT-based authentication
- OAuth2 login (Google & GitHub)
- Role-based authorization (ADMIN, DOCTOR, USER)
- PostgreSQL database integration
- Fully Dockerized multi-container setup
- Environment-based configuration (prod profile)
- Clean layered architecture

---

## 📂 Project Structure

```
src/
 ├── config/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── security/
 └── exception/
```

---

## ⚙️ Environment Variables

Create a `.env` file in the project root:

```
DB_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret
OAUTH_CLIENT_SECRET=your_oauth_secret
GITHUB_CLIENT_SECRET=your_github_secret
SPRING_PROFILES_ACTIVE=prod
```

⚠️ Do NOT commit `.env` to GitHub.

---

## 🐳 Running with Docker (Recommended)

### 1️⃣ Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/hospital-management-system.git
cd hospital-management-system
```

### 2️⃣ Create `.env`

Copy example:

```bash
cp .env.example .env
```

Update values inside `.env`.

### 3️⃣ Build & Run

```bash
docker compose up --build
```

Application will be available at:

```
http://localhost:8080/api/v1
```

---

## 🛠 Running Without Docker

### 1️⃣ Build Project

```bash
mvn clean package -DskipTests
```

### 2️⃣ Run Application

```bash
java -jar target/hospital-management-0.0.1-SNAPSHOT.jar
```

---

## 🗄 Database Configuration

- Database: PostgreSQL 15
- Container Name: hospital-db
- Volume: postgres-data
- Hibernate DDL mode: validate

---

## 🔐 Security Architecture

- Stateless session management
- JWT token validation filter
- OAuth2 authentication success handler
- Password encryption using BCrypt
- Role-based endpoint protection

---

## 📌 Future Improvements

- Swagger / OpenAPI documentation
- Flyway database migrations
- CI/CD pipeline (GitHub Actions)
- Unit & Integration testing
- Cloud deployment (AWS / Render / Railway)

---

## 👨‍💻 Author

Himanshi Modi  
Backend Developer | Java | Spring Boot | Security | Docker

---

## ⭐ Contributing

Pull requests are welcome. For major changes, please open an issue first.

---

## 📄 License

This project is licensed under the MIT License.
