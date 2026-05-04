# 📋 test12_board

Spring Boot 기반의 게시판(Board) + 회원(Member) 관리 웹 애플리케이션입니다.

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.11 |
| ORM | Spring Data JPA (Hibernate) |
| View | Thymeleaf |
| Database | MySQL |
| Build Tool | Gradle |
| Etc | Lombok, Spring DevTools |

---

## 📁 프로젝트 구조

```
src/main/java/com/example/demo/
├── controller/
│   ├── HomeController.java         # 홈 페이지 라우팅
│   ├── BoardController.java        # 게시글 등록
│   ├── BoardListController.java    # 게시글 목록 (페이징)
│   └── MemberController.java       # 회원 가입
├── service/
│   ├── BoardService.java           # 게시글 비즈니스 로직 (CRUD)
│   └── MemberService.java          # 회원 비즈니스 로직
├── repository/
│   ├── BoardRepository.java        # 게시글 JPA Repository
│   └── MemberRepository.java       # 회원 JPA Repository
├── entity/
│   ├── Board.java                  # 게시글 엔티티
│   └── Member.java                 # 회원 엔티티
├── dto/
│   ├── BoardDto.java               # 게시글 DTO
│   ├── MemberDto.java              # 회원 DTO
│   └── PageResultDto.java          # 페이징 결과 DTO
└── Test12BoardApplication.java     # Spring Boot 메인 클래스
```

---

## 🗄 데이터 모델

### Member (회원)
| 필드 | 타입 | 설명 |
|------|------|------|
| id | String | 회원 ID (PK) |
| pwd | String | 비밀번호 |
| email | String | 이메일 |
| age | int | 나이 |
| regdate | Date | 가입일 (자동 생성) |

### Board (게시글)
| 필드 | 타입 | 설명 |
|------|------|------|
| num | Long | 게시글 번호 (PK, Auto Increment) |
| member | Member | 작성자 (FK, ManyToOne) |
| title | String | 제목 |
| content | String | 내용 (Lob) |
| regdate | LocalDateTime | 작성일 (자동 생성) |

---

## 🌐 주요 URL

| Method | URL | 설명 |
|--------|-----|------|
| GET | `/` | 홈 페이지 |
| GET | `/member/join` | 회원 가입 폼 |
| POST | `/member/join` | 회원 가입 처리 |
| GET | `/board/insert` | 게시글 작성 폼 |
| POST | `/board/insert` | 게시글 등록 처리 |
| GET | `/board/list?page={n}` | 게시글 목록 (페이징) |
| GET | `/board/list1` | 게시글 목록 (@PageableDefault 방식) |

---

## ⚙️ 주요 기능

- **회원 가입**: 아이디, 비밀번호, 이메일, 나이 입력 후 등록
- **게시글 CRUD**: 등록 / 조회 / 수정 / 삭제 기능
- **페이징 처리**: 한 페이지당 5건, 페이지 번호/범위 계산 (`PageResultDto`)
- **연관관계**: `Board` ↔ `Member` 다대일(ManyToOne) 관계

---

## 🚀 실행 방법

### 1. MySQL 데이터베이스 준비
```sql
CREATE DATABASE mydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. `application.properties` 설정 확인
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

### 3. 프로젝트 빌드 및 실행
```bash
./gradlew bootRun
```

### 4. 브라우저 접속
```
http://localhost:8080
```

---

## 📝 참고사항

- JPA의 `ddl-auto=update` 설정으로 애플리케이션 실행 시 테이블이 자동으로 생성/갱신됩니다.
- 게시글 작성 시 반드시 존재하는 회원 ID를 입력해야 합니다.
- `spring-boot-devtools` 의존성이 포함되어 있어 개발 중 자동 재시작을 지원합니다.
