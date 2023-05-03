# School System API

A RESTful API for managing students, subjects, exam results, and student rankings in a school system.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)

## Features

- Manage students (add, update, delete, and view)
- Manage exam results (add, update, delete, and view)
- Calculate average marks for each subject
- Rank students based on their exam results

## Technologies

- Java 8
- Spring Boot
- Spring Data JPA
- H2 (in-memory database)

## Installation

1. Clone the repository:


2. Import the project into your favorite IDE.

3. Configure the build system and resolve the required dependencies (use Maven or Gradle).

4. Run the application using the IDE's built-in tools or via the command line:


## Usage

http://localhost:8080/swagger-ui/index.html#

Once the application is running, you can use an API client like Postman or Curl to make requests to the available API endpoints.

## API Endpoints

### Students

- `GET /api/students`: Get all students
- `GET /api/students/{id}`: Get a specific student by ID
- `POST /api/students`: Add a new student
- `PUT /api/students/{id}`: Update an existing student
- `DELETE /api/students/{id}`: Delete a specific student
- `GET /api/students/name/{name}`: Get students by name

### Exam Results

- `GET /api/exam-results`: Get all exam results
- `GET /api/exam-results/{id}`: Get a specific exam result by ID
- `POST /api/exam-results`: Add a new exam result
- `PUT /api/exam-results/{id}`: Update an existing exam result
- `DELETE /api/exam-results/{id}`: Delete a specific exam result
- `GET /api/exam-results/subject/{subject}`: Get exam results by subject
- `GET /api/exam-results/student/{studentId}`: Get exam results by student
- `GET /api/exam-results/average-marks`: Get average marks for each subject

### Rankings

- `GET /api/rankings`: Get student rankings based on their exam results

_Note: Replace the placeholders `{id}`, `{name}`, `{subject}`, and `{studentId}` with appropriate values when making requests._

