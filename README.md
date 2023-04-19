# Drone-service
Medication Transportation using the drone facility

# Build Instructions
To build the application, run the command: mvn clean install

# Run Instructions
Open terminal and run command mvn spring-boot:run or open the Drone Service application, in your IDE

    1. From the main menu, select Run | Edit Configurations
    2. Click the Add button and select the configuration type that corresponds to your application
    3. Run configuration 

# Accessing Swagger
To access Swagger, navigate to: http://localhost:8081/swagger-ui/index.html

# Accessing the In-Memory H2 Database
To access the in-memory H2 database, go to: http://localhost:8081/h2-console

# Assumptions Made:

    1.You can load more medications in an IDLE, LOADING & LOADING state
    2.Medications are registered separately and added to drones using their ids
    3.You can add medications with the same ID to the same drone 


