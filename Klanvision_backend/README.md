# Klanvision Backend - Spring Boot & MySQL

This is the backend for the Klanvision website, handling Job Applications and Admin functionality.

## Prerequisites
- Java 21
- MySQL Server
- Maven (optional, you can use the wrapper if provided)

## Database Setup
1. Create a database named `klanvision_db` in MySQL.
2. Update `src/main/resources/application.properties` with your MySQL username and password.

## API Endpoints

### Job Applications
- `POST /api/applications` - Submit a new application (Multipart Form Data)
- `GET /api/applications` - Get all applications (Admin)
- `GET /api/applications/resume/{id}` - Download resume (Admin)
- `DELETE /api/applications/{id}` - Delete an application (Admin)

### Job Listings
- `GET /api/jobs` - Get active job listings for the frontend
- `GET /api/jobs/all` - Get all listings (Admin)
- `POST /api/jobs` - Create new job (Admin)
- `PUT /api/jobs/{id}` - Update job (Admin)
- `DELETE /api/jobs/{id}` - Delete job (Admin)

### Admin
- `POST /api/admin/login` - Authenticate admin
- `POST /api/admin/setup` - Create initial admin account

## Running the Application
Run the following command in the project root:
```bash
./mvnw spring-boot:run
```
Or if you have maven installed:
```bash
mvn spring-boot:run
```

## Connecting Frontend
To connect the frontend, update the API base URL in your React components to `http://localhost:8080`.
