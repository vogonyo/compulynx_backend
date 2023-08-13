# Compulyx Backend Project

Welcome to the Compulyx Backend Project! This guide will help you get the project up and running quickly.

## Prerequisites

Before you start, ensure you have the following:

- Java 17 or later installed on your computer.
- Internet browser for API testing.

## Quick Start

1. **Download the Project**:
   - Click the green "Code" button above and choose "Download ZIP".
   - Extract the downloaded ZIP file to a convenient location.

2. **Run the Project**:
   - Open a terminal or command prompt.
   - Navigate to the project directory (where the `pom.xml` file is located).

3. **Run the Project with Maven**:
   - Run the following command to start the project with the embedded H2 in-memory database:
     ```
     mvn spring-boot:run
     ```

4. **Explore the APIs**:
   - Open a web browser.
   - Go to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to access the API documentation.
   - Use the Swagger UI to see available endpoints, test them, and see example request and response formats.

## Optional Steps

- **API Testing Tools**: You can use tools like [Postman](https://www.postman.com/downloads/) to test APIs interactively.
- **Logging**: The project logs API requests and responses. You can find the logs in `C:\var\log\applications\API\logs`.
- **Security**: The project uses JWT-based security. You don't need to configure it, but it's there to protect the APIs.

## Need Help?

For any questions or assistance, feel free to contact [vogonyo101@gmail.com]. Enjoy using the Compulyx Backend Project!

---
**Note**: Replace `[vogonyo101@gmail.com]` with your actual contact information.
