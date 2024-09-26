# Libra - Library Management System 📚

Libra is a library management system designed to streamline the processes of managing books, authors, readers, and their associated cards. This application features a user-friendly interface built with Angular and DaisyUI for the front end, while the back end is powered by Spring Boot.


> [!The idea behind this project]
> The inspiration behind this application is rooted in the love for books and the desire to make library management more efficient, modern, and accessible. Whether you’re a librarian keeping track of a vast collection, a reader exploring new genres, or an author sharing your latest masterpiece, this system is designed to simplify your experience. By blending the power of technology with the charm of libraries, it creates a space where users can effortlessly manage, discover, and engage with books and authors. At its heart, this application is about fostering a community of knowledge and making the world of books easier to navigate for everyone—from seasoned librarians to enthusiastic readers.


![Project Mockup](https://github.com/iva7777/Book-Library-Management-System/blob/main/assets/libra-mockup.jpg?raw=true)

## Table of Contents 📋

1. [Features](#features)
   - [User Authentication and Authorization](#user-authentication-and-authorization)
   - [Book Management](#book-management)
   - [Author Management](#author-management)
   - [User Management](#user-management)
   - [Reader Card Management](#reader-card-management)
   - [Reader Management](#reader-management)
2. [Technology Stack](#technology-stack)
3. [Backend Architecture](#backend-architecture)
4. [Frontend Architecture](#frontend-architecture)
5. [Installation](#installation)
6. [Running the Application](#running-the-application)
7. [Testing](#testing)
## Features 🪄

### Use Cases Overview 🧑‍🤝‍🧑

![Use Case Diagram](https://github.com/iva7777/Book-Library-Management-System/blob/main/assets/use-case.png?raw=true)

#### User Authentication and Authorization 👤
- **Login and Registration:** Users can create accounts and log in to access the system.
- **Role-based Access Control:** Users are assigned roles (Librarian and Reader), determining their permissions within the system.

#### Book Management 📘
- **Add a New Book:** Librarians can add new books to the library.
- **View Book Details:** Users can view detailed information about each book.
- **Edit Book Information:** Update existing book details as necessary.
- **Delete a Book:** Remove books that are no longer available.
- **List All Books:** Display a comprehensive list of all books in the library.
- **Search and Filter Books:** Users can search for books by title, author, and genre.

#### Author Management 👨‍🏫
- **Add a New Author:** Librarians can add authors to the database.
- **View Author Details:** Detailed information about each author can be accessed.
- **Edit Author Information:** Update existing author details.
- **Delete an Author:** Remove authors from the system.
- **List All Authors:** Display a comprehensive list of all authors.

#### User Management 👤
- **View User Details:** Librarians can view detailed information about each user.
- **Edit User Information:** Update existing user details as necessary.
- **Delete a User:** Remove users that are no longer available.
- **List All Users:** Display a comprehensive list of all users in the library.
- **Search and Filter Users:** Librarians can search for users by role.

#### Reader Card Management 🪪
- **Add a New Reader Card:** Librarians can create new reader cards for readers.
- **View Reader Card Details:** Users can view detailed information about their own reader cards.
- **Edit Reader Card Information:** Update existing reader card details as necessary.
- **Delete a Reader Card:** Remove reader cards that are no longer needed.
- **List All Reader Cards:** Display a comprehensive list of all reader cards in the library.

#### Reader Management 🤸‍♂️
- **Add a New Reader:** Librarians can create new readers.
- **View Reader Details:** Librarians can view detailed information about every reader.
- **Edit Reader Information:** Update existing reader details as necessary.
- **Delete a Reader:** Remove readers that are no longer needed.
- **List All Readers:** Display a comprehensive list of all readers in the library.
- **Search and Filter Readers:** Librarians can search for readers.

### Database Schema Overview 🗃️

![Database Diagram](https://github.com/iva7777/Book-Library-Management-System/blob/main/assets/db-diagram.png?raw=true)

#### APP_USER Table

| Column Name | Data Type   | Description                                          |
|-------------|-------------|------------------------------------------------------|
| ID          | INT         | Unique identifier for each user (auto-incremented). |
| USERNAME    | VARCHAR(20) | The username of the user (must be unique).           |
| PASSWORD    | VARCHAR(20) | The password for the user account.                    |
| ROLE        | ENUM        | Defines the user's role in the system; can be either `reader` or `librarian`. |

##### Purpose:
This table stores user credentials and roles, enabling different levels of access within the application. 

---

#### READER Table

| Column Name  | Data Type   | Description                                         |
|--------------|-------------|-----------------------------------------------------|
| ID           | INT         | Unique identifier for each reader (auto-incremented). |
| FIRST_NAME   | VARCHAR(30) | The first name of the reader.                      |
| LAST_NAME    | VARCHAR(30) | The last name of the reader.                       |
| PHONE        | VARCHAR(15) | Contact phone number of the reader.                |
| ADDRESS      | VARCHAR(80) | Residential address of the reader.                 |
| EMAIL        | VARCHAR(255)| Email address of the reader.                       |
| APP_USER_ID  | INT         | Foreign key referencing the APP_USER table (links to the user's account). |

##### Purpose:
This table holds personal information about readers, allowing the system to manage reader profiles and link them to their respective user accounts.

---

#### BOOK Table

| Column Name        | Data Type   | Description                                           |
|--------------------|-------------|-------------------------------------------------------|
| ID                 | INT         | Unique identifier for each book (auto-incremented).   |
| TITLE              | VARCHAR(30) | The title of the book.                               |
| STATUS             | ENUM        | The current status of the book; can be `available`, `borrowed`, or `discarded`. |
| YEAR_OF_PRODUCTION  | INT        | The year the book was published.                     |
| ISBN               | VARCHAR(15) | The International Standard Book Number (ISBN) for the book. |
| GENRE              | VARCHAR(20) | The genre of the book (e.g., Fiction, Non-Fiction). |
| PUBLISHER          | VARCHAR(20) | The publisher of the book.                           |
| DESCRIPTION        | VARCHAR(255)| A brief description of the book.                    |

##### Purpose:
This table maintains the inventory of books, allowing for detailed information on each book, including its status, genre, and publishing details.

---

#### AUTHOR Table

| Column Name | Data Type   | Description                                          |
|-------------|-------------|------------------------------------------------------|
| ID          | INT         | Unique identifier for each author (auto-incremented). |
| FIRST_NAME  | VARCHAR(30) | The first name of the author.                        |
| LAST_NAME   | VARCHAR(30) | The last name of the author.                         |
| BIO         | VARCHAR(255)| A brief biography of the author.                     |

##### Purpose:
This table stores information about authors, enabling the system to associate books with their respective authors.

---

#### AUTHOR_BOOK Table (Junction Table)

| Column Name     | Data Type | Description                                          |
|------------------|-----------|------------------------------------------------------|
| AUTHOR_ID        | INT       | Foreign key referencing the AUTHOR table (links to the author). |
| BOOK_ID          | INT       | Foreign key referencing the BOOK table (links to the book).      |
| CREATION_DATE    | TIMESTAMP | The date and time when the association was created. |

##### Purpose:
This table establishes a many-to-many relationship between authors and books, allowing for books to have multiple authors and vice versa.

---

#### READER_CARD Table

| Column Name      | Data Type   | Description                                          |
|------------------|-------------|------------------------------------------------------|
| ID               | INT         | Unique identifier for each reader card (auto-incremented). |
| READER_ID        | INT         | Foreign key referencing the READER table (links to the reader). |
| RENT_BOOK_ID     | INT         | Foreign key referencing the BOOK table (links to the rented book). |
| RENT_DATE        | DATE        | The date when the book was rented.                  |
| RETURN_DATE      | DATE        | The date when the book was returned.                |

##### Purpose:
This table tracks the borrowing activities of readers, linking them to the books they have rented and managing rental dates for accountability.

---
## Technology Stack 👩🏼‍💻
- **Back-end:** Spring Boot
- **Front-end:** Angular, DaisyUI, TailwindCSS
- **Database:** H2
- **Security:** Spring Security for authentication and authorization, JWT tokens
- **Build Tools:** Maven for Spring Boot, Angular CLI for Angular
- **Testing Frameworks:** 
  - JUnit for back-end testing
  - Karma for front-end testing
---
## Backend Architecture 🗄️

The backend is designed using **Spring Boot**, following a **layered architecture** to handle the core features of the Library Management System, including user authentication, book management, and search functionalities. Below is a breakdown of the key modules and folders that make up the backend architecture:

### Folder Structure Overview 🗃️

1. **Common**
   - **Subfolder: Response**
     - `ApiResponse<T>`: A generic class to parse JSON responses.
   - **Subfolder: Util**
     - `ResponseHelper`: Provides utility methods for generating common API responses:
       - `generateResponse()`: Standardizes API responses.
       - `successResponse()`: Handles successful responses.
       - `failureResponse()`: Manages failure responses.
       - `notFoundResponse()`: Handles 404 not found responses.
   - **BookStatusDeserializer**: Helps parse the `BookStatus` enum value to a JSON object for easier fetching.

2. **Config**
   - **OpenAPIConfig**: Configures **Swagger UI** with JWT token authorization for API documentation and testing.

3. **Controller**
   - This folder contains all the RESTful endpoints for various resources:
     - `AppUserController`
     - `AuthorController`
     - `BookController`
     - `ReaderCardController`
     - `ReaderController`

4. **DTO (Data Transfer Objects)**
   - Stores all DTOs used to send and receive data from the database. DTOs are used to decouple entity representation from request/response data structures.

5. **Exception**
   - Contains global and custom exceptions for better error handling:
     - `GlobalExceptionHandler`: Handles all uncaught exceptions.
     - Custom exceptions include:
       - `BookNotAvailableException`
       - `NameAlreadyTakenException`
       - `NotFoundException`

6. **Mapper**
   - Holds the mappers that convert entities to DTOs and vice versa for clean and efficient data transformation.

7. **Model**
   - Contains all the entities (JPA models) used in the system:
     - `AppUser`, `Book`, `Author`, `Reader`, `ReaderCard`
     - **Enums**: 
       - `Role`: Enum for user roles (e.g., librarian, reader).
       - `BookStatus`: Enum representing the various states a book can have (available, borrowed, discarded.).

8. **Repository**
   - Contains all `JPARepositories` that interface with the database for CRUD operations on the entities.

9. **Search**
   - Contains request records used in search functionalities within services and controllers:
     - `BookSearchRequest`
     - `ReaderSearchRequest`

10. **Service**
    - **Subfolder Interface**: Defines the abstraction layer for service methods (CRUD and search).
    - **Implementation**: Contains the concrete implementations for the service interfaces, handling core logic for operations and searches.

11. **Security**
    - **Config**: 
      - `AppConfig`: Defines beans for `AuthenticationManager`, `AuthenticationProvider`, `PasswordEncoder`, and a custom `UserDetailsService`.
      - `JwtAuthFilter`: A filter that handles JWT-based authentication and authorization.
      - `JwtService`: Provides methods to generate tokens, validate tokens, and extract claims.
      - `SecurityConfig`: Defines security policies, including which endpoints require authentication and what mechanisms are used.
    - **AuthenticationController**: Provides endpoints for user authentication (`login`, `register`, `logout`) using JWT.
    - **AuthenticationService**: Implements the business logic for user authentication and token management.
    - **Custom UserDetailsService**: A custom implementation of `UserDetailsService` for loading user data based on username.

### Security Architecture 👮

- **JWT Authentication**: The backend uses JSON Web Tokens (JWT) for authentication and authorization. Key components include:
  - **JwtAuthFilter**: Intercepts HTTP requests and checks for JWT tokens in the Authorization header.
  - **JwtService**: Handles token generation, validation, expiration checks, and extraction of user information from the JWT.
  - **SecurityConfig**: Defines security policies, restricting access to specific endpoints based on user roles (e.g., `librarian`, `reader`).

### API Documentation 🗎

- **Swagger UI**: Integrated with JWT token support via **OpenAPIConfig**, allowing authorized users to interact with and test the API.

---

## Frontend Architecture 🎀

The frontend of this project is developed using **Angular** and styled with **DaisyUI** and **TailwindCSS**. The project is organized using a **core-features-shared** architecture, which allows for better separation of concerns and easier maintainability.

### Folder Structure Overview 🗃️

1. **Core**
   - This folder contains the fundamental features of the application, such as:
     - **Authentication Service**: Manages user login, registration, and token handling.
     - **Guards**: Route guards that restrict access based on authentication and user roles.
     - **Login and Register Components**: Responsible for user authentication.
     - **Navigation & Footer Components**: The main layout components that are used throughout the app.
     - **Pagination Component**: Handles pagination functionality for tables and lists.
     - **Token Interceptor**: Intercepts HTTP requests to include JWT tokens in the headers.
     - **Models**: Angular models that mirror the backend models (e.g., `AppUser`, `Book`, `Author`, `Reader`, `ReaderCard`).

2. **Features**
   - This folder contains the main components responsible for the app's key functionalities:
     - **Users, Authors, Books, Readers and ReaderCards Components**: Responsible for CRUD operations and search functionalities.
     - **Services**: Each feature has its respective service that handles API communication for data retrieval and manipulation.
     - **Search Models**: Custom models for handling unified searches in the app (e.g., searching for books or readers).

3. **Homepage**
   - A static page independent of the backend, used for introductory or landing purposes.

4. **Shared**
   - This folder contains components and utilities that are used throughout the application:
     - **Loading Animation**: A service and interceptor to show a loading spinner during API requests.
     - **Wrong Credentials Modal**: Displays a modal when incorrect login information is provided.
     - **Error Pages**: Custom components for handling errors such as `403 Forbidden`, `404 Not Found`, and `500 Internal Server Error`.
     - **Custom Pipes**: Pipes for formatting data, such as date formats or text transformations, in the UI.

5. **AppComponent**
   - The root component of the Angular application, responsible for initializing and setting up global features.

6. **Config**
   - Contains configurations for HTTP communication, including:
     - **HttpClient**: Used to handle all HTTP requests and responses in the application.
     - **Interceptors**: Manages token injection and error handling during HTTP requests.
     - **Routers**: Defines routing for navigation within the app.

7. **Routes**
   - Contains all the paths for the application’s navigation. Each feature or component has its own route for easier navigation and component access.

8. **TailwindCSS Configuration**
   - **tailwind.config.js**: Configuration for **TailwindCSS** and **DaisyUI**, ensuring consistent and responsive styling throughout the app. Customizations for DaisyUI components are made here to enhance the look and feel of the application.

---
## Installation 💽

To get started with Libra, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/iva7777/Book-Library-Management-System.git
   cd libra```
 
 2. Set Up the Back-end:
 
 - Navigate to the back-end directory:
 ```bash
   cd main/java
```

- Build the project with Maven:
 ```bash
   mvn clean install
``` 

3. Set Up the Front-end:
- Navigate to the front-end directory:
 ```bash
   cd frontend
```

- Install the dependencies:
 ```bash
   npm install
```
---
## Running the Application ✈️

1. Start the Back-end Server:
	- Run the Spring Boot application:
```bash
   mvn spring-boot:run
```
- The server will start on http://localhost:8080.

2. Start the Front-end Application:
	- Navigate to the frontend directory and run:
```bash
   ng serve
```
- The app will start on http://localhost:4200.

3. **Database Initialization:**
- The application uses an in-memory H2 database. To access the H2 console, visit http://localhost:8080/h2-console and use the following JDBC URL: `jdbc:h2:mem:testdb`.
---
## Testing ✍🏻

The front-end has been equipped with tests to ensure the functionality of components and services. To run the tests:

1. For Front-end Tests (using Karma):
```bash
   ng test
```

2. For Back-end Tests (using JUnit):
```bash
   mvn test
```
