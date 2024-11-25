# Match and Odds Management API

## **Overview**
This project is a RESTful API built with **Spring Boot**, **PostgreSQL**, and **Docker**. It provides endpoints to manage sports matches and their associated betting odds. The API includes full CRUD functionality and can run autonomously in a Docker container.

---

## **Features**
- Create, read, update, and delete **matches** and **match odds**.
- Database integration with **PostgreSQL**.
- Enum mapping for sports (`FOOTBALL`, `BASKETBALL`) stored as integers.
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
| Field         | Type     | Description                          |
|---------------|----------|--------------------------------------|
| id            | Integer  | Unique identifier for the match.     |
| description   | String   | Description of the match.            |
| match_date    | Date     | Date of the match.                   |
| match_time    | Time     | Time of the match.                   |
| teamA         | String   | Name of team A.                      |
| teamB         | String   | Name of team B.                      |
| sport         | Enum     | Sport type (`FOOTBALL` or `BASKETBALL`). |

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
| HTTP Method | Endpoint         | Description                           |
|-------------|------------------|---------------------------------------|
| GET         | `/matches`       | Retrieve all matches.                 |
| GET         | `/matches/{id}`  | Retrieve a match by ID.               |
| POST        | `/matches`       | Create a new match.                   |
| PUT         | `/matches/{id}`  | Update an existing match.             |
| DELETE      | `/matches/{id}`  | Delete a match by ID.                 |

### Match Odds Endpoints
| HTTP Method | Endpoint                      | Description                           |
|-------------|-------------------------------|---------------------------------------|
| GET         | `/match-odds/{matchId}/odds`  | Retrieve odds for a specific match.   |
| POST        | `/match-odds/{matchId}/odds`  | Add odds to a specific match.         |
| DELETE      | `/match-odds/{id}`            | Delete odds by ID.                    |

---

## **Getting Started**
### **Prerequisites**
- **JDK 17** or later.
- **PostgreSQL** installed or accessible.
- **Docker** (optional, for containerized deployment).
- **Maven** for building the application.

---

### **Running Locally**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
