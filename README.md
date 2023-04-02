#Drones

##Description
This project is a REST API service built with Java 17 and Spring Boot. The service manages a fleet of drones capable of delivering small items, specifically medications, to locations with difficult access. The API provides endpoints for registering a drone, loading medication items onto a drone, checking loaded medication items for a given drone, checking available drones for loading, and checking drone battery level for a given drone.

The API uses JSON for input and output data. The API includes a periodic task to check drones' battery levels and create a history/audit event log.

To use the service, simply download and install Java 17 and Spring Boot, then clone the project and run it locally. The project uses an in-memory database for reference tables and dummy data.

Overall, this service provides a simple and efficient solution for managing a fleet of drones for medication delivery, with robust error handling and periodic battery checks to ensure optimal performance.

##Installation
To run this project, you will need to have Java 17 and Spring Boot installed on your machine.

Clone the repository and navigate to the root directory of the project. Run the following command to start the application:

./mvnw spring-boot:run
The application will start on http://localhost:8080.

##API's
There is a file in the root project called Drones.postman_collection.json with some apis examples to run in postman.