#Drones

##Description
This project is a REST API service built with Java 17 and Spring Boot. <br>The service manages a fleet of drones capable of delivering small items, specifically medications, to locations with difficult access. <br>The API provides endpoints for registering a drone, loading medication items onto a drone, checking loaded medication items for a given drone, checking available drones for loading, and checking drone battery level for a given drone.
<br>
The API uses JSON for input and output data. <br>The API includes a periodic task to check drones' battery levels and create a history/audit event log.
<br>
To use the service, simply download and install Java 17 and Spring Boot, then clone the project and run it locally. <br>The project uses an in-memory database for reference tables and dummy data.
<br>
Overall, this service provides a simple and efficient solution for managing a fleet of drones for medication delivery, with robust error handling and periodic battery checks to ensure optimal performance.
<br>
##Installation
To run this project, you will need to have Java 17 and Spring Boot installed on your machine.
<br>
Clone the repository and navigate to the root directory of the project. Run the following command to start the application:
<br>
./mvnw spring-boot:run
<br>
The application will start on http://localhost:8080.
##API's
There is a file in the root project called Drones.postman_collection.json with some apis examples to run in postman.

###Endpoints 

###Drones:

**Create new drone**<br>
Endpoint:<br> 
POST http://localhost:8080/drones
<br>Request body:<br>
{<br>
"serialNumber": "1234-56789",<br>
"model": "LIGHTWEIGHT",<br>
"weightLimit": 30,<br>
"batteryCapacity": 57<br>
}<br>

**Add medication to drone**<br>
Endpoint: <br>
POST http://localhost:8080/drones/{medicationCode}/{droneSerialNumber}/add-medication

**Get loaded medications of a drone**<br>
Endpoint: <br>
GET http://localhost:8080/drones/{serialNumber}/loaded-medications

**Get drones available for loading (in state IDLE)**<br>
Endpoint: <br>
GET http://localhost:8080/drones/available-for-loading

**Get battery level of a specific drone**<br>
Endpoint: <br>
GET http://localhost:8080/drones/{serialNumber}/battery-level

**Get a drone**<br>
Endpoint: <br>
GET http://localhost:8080/drones/{serialNumber}

**Update a drone**<br>
Endpoint: <br>
PUT http://localhost:8080/drones/
<br>Request body:<br>
{<br>
"serialNumber": "1234-56789",<br>
"model": "LIGHTWEIGHT",<br>
"weightLimit": 30,<br>
"batteryCapacity": 57<br>
}<br>


**Delete a drone**<br>
Endpoint: <br>
DELETE http://localhost:8080/drones/{serialNumber}

**Get all drones**<br>
Endpoint: <br>
GET http://localhost:8080/drones/all

**Change a specific drone status**<br>
Endpoint: <br>
PUT http://localhost:8080/drones/{droneSerialNumber}/{newState}

**Remove a medication from a drone**<br>
Endpoint: <br>
DELETE http://localhost:8080/drones/{serialNumber}/{medicationCode}

**Remove all medication from a drone**<br>
Endpoint: <br>
DELETE http://localhost:8080/drones/{serialNumber}/medications

###Medications:
**Create new medication**<br>
Endpoint:<br>
POST http://localhost:8080/medications
<br>Request body:<br>
{<br>
    "name": "Test4",<br>
    "weight": 14,<br>
    "code": "B",<br>
"image": "etst"<br>
}<br>


**Get medication by code**<br>
Endpoint: <br>
GET http://localhost:8080/medications/{medicationCode}

**Modify medication**<br>
Endpoint: <br>
PUT http://localhost:8080/medications
<br>Request body:<br>
{<br>
"name": "Test4",<br>
"weight": 14,<br>
"code": "B",<br>
"image": "etst"<br>
}<br>


**Delete medication. Requires that the medication is not being used**<br>
Endpoint: <br>
DELETE http://localhost:8080/medications/{medicationCode}

**Get all medications**<br>
Endpoint: <br>
GET http://localhost:8080/medications/all

###BatteryLogs:

**Get all batteryLogs between 2 dates**<br>
Endpoint: <br>
GET http://localhost:8080/batteryLogs/{dateFrom}/{dateTo}/all
<br>Aclaration: dateFrom and dateTo must have the following format: YYYY-MM-DDThh:mm:ss<br>

**Get batteryLogs between 2 dates for specific drone**<br>
Endpoint: <br>
GET http://localhost:8080/batteryLogs/{dateFrom}/{dateTo}/{droneSerialNumber}
<br>Aclaration: dateFrom and dateTo must have the following format: YYYY-MM-DDThh:mm:ss<br>

**Delete specific batteryLog**<br>
Endpoint: <br>
DELETE http://localhost:8080/batteryLogs/{batteryLogID}






