https://roadmap.sh/projects/personal-blog

# Personal Blog

This project is a personal blog web application where users can read published articles and an authenticated administrator can create, edit, and delete articles.

The application is divided into two main sections: a public guest section and a protected admin section.

## Features

### Guest Section

The guest section can be accessed by anyone without authentication.

- View the blog home page.
- View a list of published articles.
- Open and read individual articles.
- View the publication date and content of each article.

### Admin Section

The admin section is protected by Spring Security and can only be accessed by an authenticated administrator.

- Login using username and password.
- Access the admin dashboard.
- View all published articles.
- Create new articles.
- Edit existing articles.
- Delete articles.
- Logout from the application.

## Technologies

- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 Database
- PostgreSQL
- Lombok
- MapStruct
- Gradle

## Project Structure

The application follows a layered architecture:

- **Controller** — Handles HTTP requests and returns the appropriate Thymeleaf views.
- **Service** — Contains the application's business logic.
- **Repository** — Responsible for communication with the database.
- **Entity** — Represents the database entities.
- **DTO** — Objects used to transfer data between application layers.
- **Mapper** — Responsible for converting entities into DTOs and vice versa.
- **Configuration** — Contains the Spring Security configuration.

## Authentication

The admin section uses Spring Security to protect restricted pages.

Users who are not authenticated can access the public pages, while the dashboard and article management pages require authentication.

The authentication flow is based on a username and password stored in the database. Spring Security uses the application's `UserService` to load the user and validate the provided credentials.

## How to Run the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/Nattyeah/personal-blog

2. Navigate to the project directory:
   ```bash
   cd personal-blog

3. Build the project using Gradle:
   ```bash
   ./gradlew clean build

   On Windows:

   gradlew.bat clean build

4. Run the application:
   ```bash
   ./gradlew bootRun

   On Windows:

   gradlew.bat bootRun

5. Open a web browser and go to:
   ```bash
   http://localhost:8080

## Application Pages
### Public Pages
- / — Home page with the list of published articles.
- /articles/{id} — Individual article page.
- /login — Administrator login page.
### Admin Pages
- /dashboard — Administrator dashboard.
- /articles/new — Create a new article.
- /articles/{id}/edit — Edit an existing article.
- /articles/{id}/delete — Delete an article.

## How It Works

The home page displays the articles available in the database.

When an administrator logs in successfully, Spring Security creates an authenticated session and redirects the user to the dashboard.

From the dashboard, the administrator can manage the articles by creating new articles, editing existing ones, or deleting them.

The public section remains accessible without authentication, while administrative operations are restricted to authenticated users.

## Database

The application uses a relational database to store users and articles.

For development purposes, H2 can be used as the database.

The main entities are:

**User** — Stores the administrator credentials.   
**Article** — Stores the title, content, and publication date of each article.

## Future Improvements

Some possible improvements for future versions include:

- Add pagination to the dashboard.
- Add article search functionality.
- Improve validation and error handling.
- Add confirmation before deleting an article.
- Add categories and tags to articles.
- Add support for multiple administrators and roles.
- Improve the frontend with a more responsive design.
- Add automated tests for controllers and services.
