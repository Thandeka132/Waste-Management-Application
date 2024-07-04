# Waste Management Application

## Overview
This project is a Waste Management Application, aimed at promoting sustainable waste management practices. It serves as an educational tool for individuals and communities, providing guidance on proper waste disposal techniques, recycling tips, and waste categorization.

## Features
- **Disposal Guidelines**: Provides guidelines on how to dispose of various types of waste responsibly.
- **Recycling Tips**: Offers tips and information to encourage recycling habits.
- **Waste Categories**: Classifies different types of waste for proper disposal and recycling.

## Technologies Used
- **Spring Boot**: Backend framework for building RESTful APIs.
- **H2 Database**: In-memory database for storing application data during development.
- **Mockito**: Mocking framework for unit testing.
- **MockMvc**: Integration testing framework for testing Spring MVC controllers.
- **Java Persistence API (JPA)**: Java standard for managing relational data in applications.

## Getting Started
To run the application locally, follow these steps:

1. **Clone the repository**:
    ```bash
   git clone <https://github.com/Thandeka132/Waste-Management-Application.git>
   cd WasteManagementApplication

2. **Build and run the application**:
    ```bash
    mvn spring-boot:run

3. **Access the application**:
   Open your web browser and go to [http://localhost:8080/h2-console]  and click on "connect" to login and view the application.

## REST API Endpoints
### Disposal Guidelines
- **GET /api/guideline**: Retrieve all disposal guidelines.
- **GET /api/guideline/{id}**: Retrieve a specific disposal guideline by ID.
- **POST /api/guideline**: Add a new disposal guideline.
- **PUT /api/guideline/{id}**: Update an existing disposal guideline by ID.
- **DELETE /api/guideline/{id}**: Delete a disposal guideline by ID.

### Recycling Tips (similar endpoints structure as Disposal Guidelines)
- **GET /api/tip**: Retrieve all recycling tips.
- **GET /api/tip/{id}**: Retrieve a specific recycling tip by ID.
- **POST /api/tip**: Add a new recycling tip.
- **PUT /api/tip/{id}**: Update an existing recycling tip by ID.
- **DELETE /api/tip/{id}**: Delete a recycling tip by ID

### Waste Categories (similar endpoints structure as Disposal Guidelines)
- **GET /api/category**: Retrieve all recycling categories.
- **GET /api/category/{id}**: Retrieve a specific recycling category by ID.
- **POST /api/category**: Add a new recycling category.
- **PUT /api/category/{id}**: Update an existing recycling category by ID.
- **DELETE /api/category/{id}**: Delete a recycling category by ID

## Testing
The application includes unit tests for the controller and service layers. To run the tests, use:
   ```bash
   mvn test
   ```

## License
This project is licensed under the [MIT License](LICENSE).

