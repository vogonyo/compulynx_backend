# Compulyx Backend Project

Welcome to the Compulyx Backend Project! This guide will help you get the project up and running quickly.

## Prerequisites

Before you start, ensure you have the following:

- Java 17 or later installed on your computer.
- [Postman](https://www.postman.com/downloads/) installed for API testing.

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
   - Open Postman.
   - Import the Postman Collection provided with this project.
   - Use the imported collection to test available endpoints, see example request and response formats.

## Optional Steps

- **API Testing Tools**: You can use tools like [Postman](https://www.postman.com/downloads/) to test APIs interactively.

- **Logging**: The project logs API requests and responses. Follow these steps to create the log directory:

   - **Windows Users**:
      - Open the File Explorer.
      - Navigate to the `C:\` drive.
      - Create a new folder named `var` (if not already present).
      - Inside the `var` folder, create a new folder named `log`.
      - Inside the `log` folder, create a new folder named `applications`.
      - Inside the `applications` folder, create a new folder named `API`.
      - Inside the `API` folder, create a new folder named `logs`.
      - The complete path should be: `C:\var\log\applications\API\logs`.
      - This is where you'll find the log files: `request.log` and `response.log`after you interact with the APIs on Postman

- **Security**: The project uses JWT-based security. You don't need to configure it, but it's there to protect the APIs.

## Need Help?

For any questions or assistance, feel free to contact us at vogonyo101@gmail.com. Enjoy using the Compulyx Backend Project!

Repository Link: [https://github.com/vogonyo/compulynx_backend](https://github.com/vogonyo/compulynx_backend)

## Need Help?

For any questions or assistance, feel free to contact [vogonyo101@gmail.com]. Enjoy using the Compulyx Backend Project!

---
**Note**: Replace `[vogonyo101@gmail.com]` with your actual contact information.
