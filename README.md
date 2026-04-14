# 📋 mcptest - Spring Boot 게시판 프로젝트

Spring Boot 기반의 게시판(Board) 웹 애플리케이션입니다.  
회원(Member)과 게시글(Board)의 CRUD 및 페이지네이션 기능을 제공합니다.

---

## 🛠️ 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.11 |
| ORM | Spring Data JPA |
| Template Engine | Thymeleaf |
| Database | MySQL |
| Build Tool | Gradle |
| Etc | Lombok, Spring DevTools |

---

## 📁 프로젝트 구조

```
src/main/java/com/example/demo/
├── controller/
│   ├── HomeController.java          # 홈 페이지 라우팅
│   ├── BoardController.java         # 게시글 등록 처리
│   ├── BoardListController.java     # 게시글 목록 및 페이지네이션
│   └── MemberController.java        # 회원 관련 처리
├── service/
│   ├── BoardService.java            # 게시글 비즈니스 로직 (CRUD)
│   └── MemberService.java           # 회원 비즈니스 로직
├── entity/
│   ├── Board.java                   # 게시글 JPA 엔티티
│   └── Member.java                  # 회원 JPA 엔티티
├── dto/
│   ├── BoardDto.java                # 게시글 데이터 전달 객체
│   ├── MemberDto.java               # 회원 데이터 전달 객체
│   └── PageResultDto.java           # 페이지네이션 결과 객체
└── repository/
    ├── BoardRepository.java         # 게시글 JPA Repository
    └── MemberRepository.java        # 회원 JPA Repository
```

---

## ⚙️ 주요 기능

### 게시판 (Board)
- **게시글 등록** : 회원 아이디 기반으로 게시글 작성
- **게시글 목록** : 페이지네이션 적용 (페이지당 5개, 최신순 정렬)
- **게시글 수정** : 제목 및 내용 수정 (Dirty Checking 방식)
- **게시글 삭제** : 글 번호 기반 삭제
- **게시글 조회** : 글 번호로 단건 조회

### 회원 (Member)
- 회원 가입 및 조회 기능

### 페이지네이션
- `PageRequest` 및 `@PageableDefault` 두 가지 방식 지원
- 시작 페이지 / 끝 페이지 / 전체 페이지 수 제공

---

## 🗄️ 엔티티 관계

```
Member (1) ──────< Board (N)
  - id (PK)           - num (PK, Auto Increment)
  - pwd               - title
  - email             - content (Lob)
  - age               - regdate (자동 생성)
  - regdate           - member (FK → Member.id)
```

---

## 🔗 주요 API 엔드포인트

| Method | URL | 설명 |
|--------|-----|------|
| GET | `/` | 홈 페이지 |
| GET | `/board/insert` | 게시글 등록 폼 |
| POST | `/board/insert` | 게시글 등록 처리 |
| GET | `/board/list?page={n}` | 게시글 목록 (PageRequest 방식) |
| GET | `/board/list1?page={n}` | 게시글 목록 (@PageableDefault 방식) |

---

## 🚀 실행 방법

### 사전 요구사항
- Java 17 이상
- MySQL 데이터베이스
- Gradle

### 1. 레파지토리 클론

```bash
git clone https://github.com/gns0704/mcptest.git
cd mcptest
```

### 2. 데이터베이스 설정

`src/main/resources/application.properties` 파일에서 DB 정보를 설정합니다.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/{데이터베이스명}
spring.datasource.username={사용자명}
spring.datasource.password={비밀번호}
```

### 3. 애플리케이션 실행

```bash
./gradlew bootRun
```

브라우저에서 `http://localhost:8080` 으로 접속합니다.

---

## 📦 의존성

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.mysql:mysql-connector-j'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
