Currency Exchange and Discount Calculation API

Overview

This Spring Boot application calculates the total payable amount for a bill in a specified currency after applying applicable discounts and converting the amount to a target currency. The application integrates with an external currency exchange API to fetch real-time exchange rates.

Features

User-based Discounts:

30% discount for store employees.

10% discount for store affiliates.

5% discount for customers with a tenure of over 2 years.

$5 discount for every $100 on the bill.

Percentage-based discounts do not apply to groceries.

Only one percentage-based discount is applied per bill.

Currency Conversion: Converts the bill amount to a target currency using exchange rates from Open Exchange Rates API.

REST API Endpoint: Allows users to submit a bill and receive the payable amount after discounts and conversion.

Security: Protects the /api/calculate endpoint using JWT authentication.

Technologies Used

Java 17

Spring Boot

Spring Security with JWT

Spring WebFlux (WebClient for API calls)

JUnit & Mockito (for testing)

Lombok

Maven

JaCoCo (for test coverage reports)

Setup Instructions

Prerequisites

JDK 17+

Maven

An API key for Open Exchange Rates API

A valid JWT token for authentication

Installation

Clone the repository:

git clone https://github.com/rahat57/currency-exchange-discount.git
cd currency-exchange-discount

Update application.properties with your API key:

exchange.api.url=https://open.er-api.com/v6/latest/
exchange.api.key=YOUR_API_KEY_HERE

generate you key on the link https://openexchangerates.org/account/app-ids

security.jwt.secret=YOUR_SECRET_KEY

Build the project:

mvn clean install

Run the application:

mvn spring-boot:run

API Endpoint

Calculate Payable Amount

Endpoint: POST /api/calculate

Authentication: Requires a valid JWT token in the Authorization header.

Request Header:

Authorization: Bearer YOUR_JWT_TOKEN

Request Body (JSON):

{
  "userType": "CUSTOMER",
  "customerSince": "2018-03-10",
  "items": [
    { "name": "Laptop", "category": "Electronics", "price": 300.0 },
    { "name": "Apple", "category": "Grocery", "price": 50.0 }
  ],
  "originalCurrency": "USD",
  "targetCurrency": "EUR"
}

Response:

{
  "totalBeforeDiscount": 350.0,
  "discountApplied": 20.0,
  "totalAfterDiscount": 330.0,
  "convertedAmount": 280.5,
  "targetCurrency": "EUR"
}

Running Tests & Generating Coverage Reports

Run unit tests:

mvn test

Generate coverage report:

mvn clean verify
mvn jacoco:report

The coverage report will be available at target/site/jacoco/index.html.
