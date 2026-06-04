# Tournament Scoreboard API

A REST API built with Java and Spring Boot to manage tournament data — teams, players, matches, and a live scoreboard.

**Tech Stack:** Java 17, Spring Boot 3.5, Spring Data JPA, Spring Security, MySQL

---

## Entities

**Player** — belongs to a team. Unique name enforced.

**Team** — tracks total score, matches played, wins, and draws across matches.

**Match** — scheduled between two teams. Moves through three states: SCHEDULED → ONGOING → COMPLETED. Scores update during the match; team stats update on completion.

**User** — stores credentials and role (ROLE_USER or ROLE_ADMIN). Username is the primary key.

---

## Features

- JWT authentication with access token (15 min) and refresh token (7 days)
- Role-based access — admin manages data, users read it
- CRUD for players, teams, and matches
- Match lifecycle management — start, end, score updates, rescheduling
- Scoreboard sorted by total score with rank and win ratio via DTO projection
- Global exception handling via `@ControllerAdvice`
- Input validation with Jakarta Validation
- Pagination on players and matches
- `FetchType.LAZY` on all ManyToOne relationships

---

## Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login and get access + refresh token |
| POST | /auth/refresh | Get new access token using refresh token |

### Admin Only
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /admin/player | Add a new player |
| DELETE | /admin/player/{id} | Delete a player |
| POST | /admin/team | Add a new team |
| PUT | /admin/team/{teamId} | Rename a team |
| DELETE | /admin/team/{teamId} | Remove a team |
| POST | /admin/team/{teamId}/player/{playerId} | Assign player to team |
| POST | /admin/match/{teamAId}/{teamBId} | Schedule a match between two teams |
| DELETE | /admin/match/{matchId} | Remove a scheduled match |
| PUT | /admin/match/start/{matchId} | Start a match |
| PUT | /admin/match/end/{matchId} | End a match and update team stats |
| PUT | /admin/match/{matchId}/{teamId}/{score} | Update team score in ongoing match |
| PUT | /admin/match/reschedule/{matchId}/{newDateTime} | Reschedule a match |

### Authenticated Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /user/player | Get all players (paginated) |
| GET | /user/player/{id} | Get player by id |
| GET | /user/team | Get all teams |
| GET | /user/team/{id} | Get team by id |
| GET | /user/match | Get all matches (paginated) |
| GET | /user/match/{id} | Get match by id |
| GET | /scoreboard | Live standings sorted by total score |

---

## Setup

Add `application.properties` with your DB credentials and JWT secret (min 32 characters):

```
spring.datasource.url=jdbc:mysql://localhost:3306/scoreboard
spring.datasource.username=root
spring.datasource.password=yourpassword
jwt.secret=yoursecretkeyatleast32charslong
```

Run with `mvn spring-boot:run`.
