# AiNotes

AiNotes is a full-stack note-taking application featuring a modern Angular frontend and a robust Spring Boot backend.
The project is designed for quick note management, easy deployment, and developer-friendly contribution.

A key feature of AiNotes is the ability to summarise all of your notes using AI, making it easy to get concise overviews
of your content.

## Features

- Fast and intuitive note-taking
- Modern Angular user interface
- RESTful API powered by Spring Boot
- Docker support for easy deployment

## Project Structure

- `frontend/` – Angular application (UI)
- `src/main/java/` – Java backend (Spring Boot)
- `src/main/resources/` – Backend configuration and resources
- `docker-compose.yml` & `Dockerfile` – Containerization and orchestration

## Prerequisites

- Node.js & npm (for frontend)
- Java 21 (for backend)
- Docker (optional, for containerized deployment)

## Environment Variables

Before starting the application, create a `.env` file at the root of the project and define the following variable:

```
OPENAI_API_KEY=your_openai_api_key_here
```

This key is required for features that interact with the OpenAI API.

## Getting Started

### With Docker

```bash
docker-compose up --build
```

This command will start the required database and pgAdmin in containers.

### Backend (manual start)

> Note: The backend requires the database to be running. You can use Docker to start the database only, or ensure it is
> available before running the backend manually.

```bash
./gradlew bootRun
```

API available at: `http://localhost:8080/`

### Frontend

```bash
cd frontend
npm install
ng serve
```

App available at: `http://localhost:4200/`

## Testing

- Backend: `./gradlew test`
- Frontend: `cd frontend && ng test`

## Contributing

Contributions are welcome! Please submit issues and pull requests for improvements or bug fixes.

