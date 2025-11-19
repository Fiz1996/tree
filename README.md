# Family Tree API (Spring Boot)

This project provides a RESTful API for creating and managing family trees.  
It supports adding people, linking parents, linking spouses, and generating a hierarchical tree structure that can be consumed by any frontend (Angular, React, etc.).

---

## 🚀 Features

- Create and manage people
- Link father/mother relationships
- Link spouses (two-way connection)
- Generate a full family tree structure (`/api/tree`)
- Simple, clean JSON tree response
- CORS-enabled for frontend integration
- Extensible architecture to support more features such as:
  - Multiple families
  - Editing and deleting members
  - More detailed person attributes
  - Searching and filtering

---

## 🧱 Tech Stack

- **Java 21+**
- **Spring Boot 3+**
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL or H2 (configurable)
- Lombok (optional)
- Jackson for JSON serialization

---

