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

**Auth** — public

- `POST /auth/register`
- `POST /auth/login`
- `POST /auth/refresh`

**Admin only**

- `/admin/player` — add, delete
- `/admin/team` — add, rename, delete, assign player
- `/admin/match` — add, delete, start, end, update score, reschedule

**Authenticated users**

- `/user/player` — read players
- `/user/team` — read teams
- `/user/match` — read matches
- `GET /scoreboard` — live standings

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
