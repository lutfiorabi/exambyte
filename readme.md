# ExamByte

**ExamByte** is a web-based quiz and grading platform designed for university-level programming courses. It simulates an *Ilias*-style test system for managing eligibility tests, enabling student testing, manual grading for instructors, and detailed result visibility.

---

## Features

### User Management
- **GitHub Authentication**
- **Role-based Authorization**:
  - Students: take quizzes and view results
  - Organizers: manage quizzes, grading, and test lifecycle
---

### 🧪 Quiz Functionality
- **Multiple Choice Questions (MCQs)** – automatically evaluated
- **Text Questions** – manually graded by organizers
- **Solution Reveal** – visible after test ends
---

### 🛠️ Test Lifecycle
- **Create Tests** – define test title and end time
- **Test Execution** – questions shown only during active period
- **Grading** – automatic for MCQs, manual for free-text
- **Results** – released automatically at result publication time

---

## 🧱 Tech Stack

- **Backend**: Java 21 + Spring Boot
- **Frontend**: HTML, Thymeleaf
- **Database**: PostgreSQL (via Docker)
- **Persistence**: Spring Data JDBC
- **Authentication**: GitHub OAuth2
- **Migrations**: Flyway


