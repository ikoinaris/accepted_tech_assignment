# Match and Odds Management API

## **Overview**
This project is a RESTful API built with **Spring Boot**, **PostgreSQL**, and **Docker**. It provides endpoints to manage sports matches and their associated betting odds. The API includes full CRUD functionality and Database Integration.

---

## **Features**
- Create, read, update, and delete **matches** and **match odds**.
- Database integration with **PostgreSQL**.
- Enum mapping for sports (`FOOTBALL`, `BASKETBALL`).
- Dockerized deployment for autonomous operation.
- Swagger UI for API documentation and testing.

---

## **Technology Stack**
- **Java** (Spring Boot)
- **PostgreSQL** (Database)
- **Docker** (Containerization)
- **Swagger** (API Documentation)
- **Maven** (Build Tool)

---

## **Entities**
### **Match**
| Field       | Type     | Description                          |
|-------------|----------|--------------------------------------|
| id          | Integer  | Unique identifier for the match.     |
| description | String   | Description of the match.            |
| matchDate   | Date     | Date of the match.                   |
| matchTime   | Time     | Time of the match.                   |
| teamA       | String   | Name of team A.                      |
| teamB       | String   | Name of team B.                      |
| sport       | Enum     | Sport type (`FOOTBALL` or `BASKETBALL`). |

### **MatchOdds**
| Field         | Type     | Description                          |
|---------------|----------|--------------------------------------|
| id            | Integer  | Unique identifier for the odds.      |
| matchId       | Integer  | Foreign key linking to a match.      |
| specifier     | String   | Bet specifier (e.g., "1", "X", "2"). |
| odd           | Decimal  | Betting odds.                        |

---

## **Endpoints**
### Match Endpoints
| HTTP Method | Endpoint         | Description               |
|-------------|------------------|---------------------------|
| GET         | `/matches`       | Retrieve all matches.     |
| GET         | `/matches/{id}`  | Retrieve a match by Id.   |
| POST        | `/matches`       | Create a new match.       |
| PUT         | `/matches/{id}`  | Update an existing match. |
| DELETE      | `/matches/{id}`  | Delete a match by Id.     |

### Match Odds Endpoints
| HTTP Method | Endpoint                     | Description                         |
|-------------|------------------------------|-------------------------------------|
| GET         | `/match-odds/`               | Retrieve all odds.                  |
| GET         | `/match-odds/{id}/`          | Retrieve an odds by Id.             |
| GET         | `/match-odds/{matchId}/odds` | Retrieve odds for a specific match. |
| POST        | `/match-odds/{matchId}/odds` | Add odds to a specific match.       |
| PUT         | `/match-odds/{id}/ `         | Update an existing match odd.       |
| DELETE      | `/match-odds/{id}`           | Delete odds by Id.                  |

---

## **Getting Started**
### **Prerequisites**
- **JDK 17**.
- **PostgreSQL** installed or accessible.
- **Docker** installed.
- **Maven** for building the application.

---

### **Running Locally**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
2. Create PostgreSQL Database named matches. Then update connection details in application.properties:
   ```
      spring.datasource.url=jdbc:postgresql://localhost:5432/matches
      spring.datasource.username=admin
      spring.datasource.password=password
      spring.jpa.hibernate.ddl-auto=update
3. Build and run the application:
   ```
      mvn clean package
      java -jar target/matchbet-0.0.1-SNAPSHOT.jar
4. Access the API (Swagger UI):
   ```
      http://localhost:8080/swagger-ui.html
