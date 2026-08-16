# MiniTwitter — Spring Boot Backend

A company-style Spring Boot REST API assignment for revising core Spring Boot backend concepts **without Spring Security/JWT**.

## 🎯 Objective

Build a simplified Twitter-like backend where users can create tweets, follow users, like tweets, comment on tweets, and view a personalized feed.

**Target time:** 7–9 hours  
**Difficulty:** Intermediate  
**Security:** Out of scope

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web / REST
- Spring Data JPA / Hibernate
- MySQL
- Bean Validation
- JUnit + Mockito
- Swagger / OpenAPI
- Maven
- Lombok (optional)

## 🏗️ Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Suggested structure:

```text
com.example.minitwitter
├── controller
├── service
│   └── impl
├── repository
├── entity
├── dto
│   ├── request
│   └── response
├── mapper
├── exception
├── specification
└── config
```

## 🗄️ Entities

### User
 

```text
id
username
email
displayName
bio
createdAt
updatedAt
```

Rules: username/email unique, username 3–30 characters, bio max 160 characters.

### Tweet

```text
id
content
author
createdAt
updatedAt
```

One User → Many Tweets. Tweet content: 1–280 characters.

### Comment

```text
id
content
user
tweet
createdAt
updatedAt
```

One User → Many Comments. One Tweet → Many Comments. Content: 1–300 characters.

### Like

```text
id
user
tweet
createdAt
```

One User → Many Likes. One Tweet → Many Likes. Same user cannot like the same tweet twice.

### Follow

```text
id
follower
following
createdAt
```

A user cannot follow himself/herself. The same follower cannot follow the same user twice.

## 🔗 Relationships

```text
USER
 │
 ├── 1 User → Many Tweets
 ├── 1 User → Many Comments
 ├── 1 User → Many Likes
 ├── 1 User → Many Follow records
 │
 └── 1 Tweet → Many Comments
     1 Tweet → Many Likes
```

Follow is a self-referencing relationship:

```text
USER → FOLLOW → USER
```

`Follow` contains `follower_id` and `following_id`.

`Like` contains `user_id` and `tweet_id`.

`Comment` contains `user_id` and `tweet_id`.

## 🌐 REST APIs

### User

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/users` | Create user |
| GET | `/api/users/{id}` | Get user |
| PUT | `/api/users/{id}` | Update profile |
| GET | `/api/users?search=&page=&size=` | List/search users |

### Tweet

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/tweets` | Create tweet |
| GET | `/api/tweets/{id}` | Get tweet |
| PUT | `/api/tweets/{id}` | Update tweet |
| DELETE | `/api/tweets/{id}` | Delete tweet |
| GET | `/api/tweets?page=0&size=10&sort=createdAt,desc` | List tweets |
| GET | `/api/tweets/search?keyword=java` | Search tweets |

### Follow

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/users/{userId}/following/{targetUserId}` | Follow |
| DELETE | `/api/users/{userId}/following/{targetUserId}` | Unfollow |
| GET | `/api/users/{userId}/following` | List following |
| GET | `/api/users/{userId}/followers` | List followers |

### Like

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/tweets/{tweetId}/likes/{userId}` | Like tweet |
| DELETE | `/api/tweets/{tweetId}/likes/{userId}` | Unlike tweet |
| GET | `/api/tweets/{tweetId}/likes/count` | Like count |

### Comment

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/tweets/{tweetId}/comments` | Create comment |
| GET | `/api/tweets/{tweetId}/comments?page=0&size=10` | List comments |
| DELETE | `/api/comments/{commentId}` | Delete comment |

### Feed

```http
GET /api/users/{userId}/feed?page=0&size=10&sort=createdAt,desc
```

Feed = user's own tweets + tweets from users they follow, newest first.

Prefer a database query/Specification instead of loading everything into Java and filtering there.

## 📦 DTOs

Minimum:

```text
CreateUserRequest
UpdateUserRequest
UserResponse
UserSummaryResponse
CreateTweetRequest
UpdateTweetRequest
TweetResponse
CreateCommentRequest
CommentResponse
PageResponse<T>
```

`TweetResponse` should contain author summary, like count and comment count without exposing the full JPA entity graph.

## ✅ Validation

Use Bean Validation such as:

```java
@NotBlank
@Size(min = 3, max = 30)
private String username;
```

```java
@NotBlank
@Email
private String email;
```

```java
@NotBlank
@Size(min = 1, max = 280)
private String content;
```

Validation failures → `400 Bad Request`.

## 🚨 Exception Handling

Create:

```text
ResourceNotFoundException
DuplicateResourceException
InvalidOperationException
```

Use:

```java
@RestControllerAdvice
```

Handle validation, not-found, duplicate/constraint, invalid-operation and generic errors.

Example:

```json
{
  "timestamp": "2026-08-09T21:00:00",
  "status": 404,
  "error": "RESOURCE_NOT_FOUND",
  "message": "Tweet not found with id: 15",
  "path": "/api/tweets/15"
}
```

## 📄 Pagination, Sorting & Filtering

Support:

```text
?page=0&size=10
?sort=createdAt,desc
?authorId=5
/api/tweets/search?keyword=spring
```

Use `Pageable` and `Page<T>`. Use `JpaSpecificationExecutor` where appropriate.

## 🗃️ JPA Requirements

Demonstrate:

- `@Entity`
- `@Id`
- `@GeneratedValue`
- `@ManyToOne`
- `@OneToMany` where appropriate
- `@JoinColumn`
- Unique constraints
- Lazy/Eager loading
- Cascade/orphanRemoval decisions
- Transactions
- JPA Auditing

Do not blindly use `EAGER`.

## 🔄 Mapper

Keep Entity ↔ DTO conversion in mapper classes.

```text
Entity → Mapper → Response DTO
Request DTO → Mapper → Entity
```

Controllers should not contain mapping-heavy logic.

## 📊 Business Rules

| Rule | Expected |
|---|---|
| Follow yourself | 400 |
| Follow same user twice | 409 |
| Like same tweet twice | Reject duplicate |
| Unlike without like | Choose/document behavior |
| Missing tweet/user/comment | 404 |
| Duplicate username/email | 409 |
| Invalid content | 400 |

## 📡 HTTP Status Codes

| Operation | Status |
|---|---|
| Create | `201 Created` |
| GET | `200 OK` |
| Update | `200 OK` |
| Delete | `204 No Content` |
| Validation | `400 Bad Request` |
| Not found | `404 Not Found` |
| Conflict | `409 Conflict` |

## 📚 Swagger / OpenAPI

Document every endpoint with:

- Summary/description
- Request body
- Parameters
- Response codes
- Request/response schemas

Typical Swagger URL:

```text
http://localhost:8080/swagger-ui/index.html
```

## 🧪 Testing

Minimum: **8–12 meaningful unit tests** using JUnit + Mockito.

Recommended:

- User creation success
- Duplicate username/email
- Get missing user
- Tweet creation
- Tweet validation/business failure
- Follow success
- Prevent self-follow
- Prevent duplicate like
- Feed retrieval with pagination
- Delete missing resource
- Validation/exception scenario

## ⚙️ Configuration

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/minitwitter
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Never commit real passwords.

## 🚀 Run

```bash
git clone <your-repository-url>
cd minitwitter
```

Create the database:

```sql
CREATE DATABASE minitwitter;
```

Run:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Run tests:

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

## ⏱️ 7–9 Hour Plan

| Time | Task |
|---|---|
| 0:00–0:30 | Requirements + ER/API design |
| 0:30–1:30 | Setup + entities + repositories |
| 1:30–2:45 | User + Tweet |
| 2:45–4:00 | Follow + Like + Comment |
| 4:00–5:00 | Feed + pagination + filtering |
| 5:00–6:00 | DTO + Mapper + Validation + Exceptions |
| 6:00–7:00 | Swagger + Logging + README |
| 7:00–8:00 | JUnit + Mockito |
| 8:00–9:00 | Debug + Refactor + Self-review |

## 📋 Completion Checklist

- [ ] Application starts successfully
- [ ] Database connection works
- [ ] User CRUD works
- [ ] Tweet CRUD works
- [ ] Follow/unfollow works
- [ ] Like/unlike works
- [ ] Comments work
- [ ] Feed works
- [ ] Pagination works
- [ ] Sorting works
- [ ] Search/filter works
- [ ] DTOs are used
- [ ] Mapper layer exists
- [ ] Validation works
- [ ] Global exception handling works
- [ ] Swagger works
- [ ] 8–12 meaningful tests pass
- [ ] README is complete
- [ ] No Spring Security/JWT
- [ ] Entities are not unnecessarily exposed

## 🏆 Self-Assessment

Total: **110 points**

| Area | Points |
|---|---:|
| Setup/configuration | 5 |
| Entity design + JPA relationships | 15 |
| Repository/query design | 10 |
| Controller/REST design | 10 |
| Service/business logic | 15 |
| DTO + Mapper | 10 |
| Validation | 5 |
| Exception handling | 10 |
| Pagination/sorting/filtering/Specification | 10 |
| Swagger/OpenAPI | 5 |
| JUnit + Mockito | 10 |
| Code quality/README | 5 |
| **TOTAL** | **110** |

| Score | Level |
|---|---|
| 95–110 | Strong |
| 80–94 | Good |
| 65–79 | Average |
| 50–64 | Needs revision |
| Below 50 | Revise fundamentals and rebuild |

Score only what you implemented independently.

## 🎤 Interview Questions

1. Why use DTOs instead of returning entities?
2. Why are Follow and Like explicit relationship entities?
3. Where should `@Transactional` be used?
4. How do you prevent duplicate likes under concurrent requests?
5. How do you enforce duplicate follows at database level?
6. Why should feed filtering happen in the database?
7. When would you use `Specification`?
8. What is the difference between `Page`, `Slice` and `List`?
9. What can go wrong with LAZY relationships during serialization?
10. Why should controllers not contain business logic?
11. What is `@RestControllerAdvice`?
12. How do validation exceptions reach the global handler?
13. What is the difference between PUT and PATCH?
14. What happens when an HTTP request reaches a Spring controller?
15. What does Spring Data JPA generate for repository methods?
16. What is the difference between `save()` and `saveAndFlush()`?
17. How would you optimize a feed with 10 million tweets?
18. How would you add authentication later without rewriting the business layer?

## 🚀 Optional Features

Only after the core 7–9 hour assignment:

- Hashtags
- Soft delete
- `@Version` optimistic locking
- Database indexes
- Integration tests
- Docker Compose
- Actuator
- Custom `PageResponse<T>`

---

> **Challenge rule:** Build first. Search only when stuck. Do not watch a complete tutorial while solving.
