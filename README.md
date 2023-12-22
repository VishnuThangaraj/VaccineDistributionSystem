# Vaccine Distribution System API

This is a Spring Boot API for a vaccine distribution system. The system aims to create vaccination appointment efficiently and provides email notifications.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  - [Authentication](#authentication)
- [Configuration](#configuration)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven
- Database (e.g., MySQL, PostgreSQL) for data storage

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/VishnuThangaraj/VaccineDistributionSystem/

## Usage
 ### API Endpoints
The API exposes the following endpoints:

POST doctor/register: Create a new Doctor to the Database.
POST patient/signup: Create a new Patient to Database.
POST patient/login: Login with Existing Patient.
GET patient/create-appointment: Create a new appointment.
POST vaccination-center/register: Create a new Vaccination Center to the Database.

### Authentication
The API uses token-based authentication. Obtain an access token and include it in the Authorization header for protected endpoints.

## Configuration
The application can be configured using the application.properties file. Update the file to set database configurations, server port, etc.

## Development
To contribute to the development of this API, follow these steps:

Fork the repository.
Create a new branch for your feature/bug fix: git checkout -b feature-name.
Make changes and commit: git commit -m "Your message".
Push to the branch: git push origin feature-name.
Submit a pull request.

## Testing
Run the tests using:

``` bash
mvn test

## Contributing
Contributions are welcome! Please follow the Development section for the contribution process.

