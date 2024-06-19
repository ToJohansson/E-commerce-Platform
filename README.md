# E-commerce-Platform

Welcome to our E-commerce Platform project! This repository contains the backend system built with Java Spring Boot, utilizing RabbitMQ as a message broker, and PostgreSQL for database management using Docker containers. The frontend is developed in React with Bootstrap for styling.

Project Structure
The project is organized into two main parts: the backend (Java Spring Boot) and the frontend (React).

Backend
The backend system is structured as a microservice architecture using Java Spring Boot. It handles various functionalities such as user authentication, product management, order processing, and interaction with the PostgreSQL database via Docker containers. Key features of the backend include:

Microservices: Each aspect of the e-commerce platform (e.g., user management, product catalog, orders) is designed as a separate microservice, promoting modularity and scalability.

RabbitMQ Integration: Asynchronous communication and event-driven architecture are implemented using RabbitMQ, ensuring efficient handling of messages and events across microservices.

PostgreSQL Database: Docker containers are used to manage PostgreSQL databases, providing flexibility and ease of deployment.

Frontend
The frontend of the platform is developed using React, a popular JavaScript library for building user interfaces, with Bootstrap for responsive and sleek styling. Key features of the frontend include:

Responsive Design: Utilizing Bootstrap ensures the frontend is responsive and adapts seamlessly to various screen sizes and devices.

User Interface: Intuitive and user-friendly interface for browsing products, managing orders, and interacting with the e-commerce platform.

API Integration: Communicates with the backend APIs to fetch and display data dynamically, ensuring real-time updates and a smooth user experience.

Getting Started
To get started with the project, follow these steps:

Clone the Repository: git clone <repository-url>

Backend Setup:

Ensure Java and Maven are installed.
Set up RabbitMQ locally or use a cloud service.
Configure PostgreSQL database in Docker containers or using a cloud service.
Navigate to the backend directory and run mvn spring-boot:run to start the backend services.
Frontend Setup:

Navigate to the frontend directory and run npm install to install dependencies.
Start the frontend development server using np
