
# Restaurant Reservation System

## Pre-requisites:
- Java Development Kit (JDK) 17+
- Apache Maven

## How to start?
1. Clone private repo into local folder
2. Start project by running `mvn spring-boot:run`
3. Access swagger UI using browser and navigating to [http://localhost:8080/swagger-ui/index.html]()

---

# Documentation

## Folder Structure
```
src
└── main
|     └── Java
|     |     └── com.xyz.reservation_system
|     |           └── common - constants
|     |           └── config - configuration files
|     |           └── exceptions - exception and error handling
|     |           └── notification - module (controller,service,repository,entity,...)
|     |           └── reservation - module (controller,service,repository,entity,...)
|     |           └── ReservationSystemApplication.java
|     └── resources
|           └── application.properties
└── test

```

## API Specifications

*Note*: API specifications are already part of the swagger documentation.

## Assumptions on the design

- Added unit tests for controller and service
- I decided to separate the **reservation** and **notification** modules into its own package
- Notification only has service since we are only printing on console
- Utilized SQLite for embedded database
- Opted to use MapStruct for easier ENTITY to DTO and vice versa conversion
- Custom default exception handling
- Entire project is a simple yet robust implementation for a reservation system