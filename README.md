This is the implementation of the backend for a mock application
I made for Quad Solutions. This app provides 2 endpoints. One endpoint
to receive a number of trivia questions, and another to check if an 
answer is correct.

To run the project type: `./mvnw spring-boot:run`

To Build the project type: `./mvnw clean package` \
To run the jar, type java: `-jar target/trivia-0.0.1-SNAPSHOT.jar`

Endpoints can be accesed at: \
http://localhost:8080/questions \
http://localhost:8080/checkanswer