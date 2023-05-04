# Drift School System

A RESTful API for managing students, subjects, exam results, and student rankings in a school system.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Demo](#demo)
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

## Demo

https://user-images.githubusercontent.com/95200602/236117774-7991d6d1-d87b-4c60-95f6-9809a71b791a.mp4


## Usage

http://localhost:8080/swagger-ui/index.html#

Once the application is running, you can use an API client like Swagger, Postman or Curl to make requests to the available API endpoints.

## API Endpoints 

### Students

- `GET /api/students`: Get all students
- `GET /api/students/{id}`: Get a specific student by ID
- `POST /api/students`: Add a new student
- `PUT /api/students/{id}`: Update an existing student
- `DELETE /api/students/{id}`: Delete a specific student

### Exam Results

- `GET /api/exam-results`: Get all exam results
- `GET /api/exam-results/{id}`: Get a specific exam result by ID
- `POST /api/exam-results`: Add a new exam result
- `PUT /api/exam-results/{id}`: Update an existing exam result
- `DELETE /api/exam-results/{id}`: Delete a specific exam result
- `GET /api/exam-results/average-marks`: Get average marks for each subject

### Rankings

- `GET /api/rankings`: Get student rankings based on their exam results

_Note: Replace the placeholders `{id}`, `{name}`, `{subject}`, and `{studentId}` with appropriate values when making requests._

