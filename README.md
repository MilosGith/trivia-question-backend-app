This is the implementation of the backend for a mock application
I made for Quad Solutions. This app provides 2 endpoints. One endpoint
to receive a number of trivia questions, and another to check if an 
answer is correct.

To run the project type:`./mvnw spring-boot:run`

To Build the project type:`./mvnw clean package` \
To run the jar, type:`java -jar target/trivia-0.0.1-SNAPSHOT.jar`

Endpoints can be accessed at: \
http://localhost:8080/questions \
http://localhost:8080/checkanswer

The questions endpoint provides a list of questions. Questions are in the following format:
```json
{
        "id": 3,
        "questionText": "In the \"Dragon Ball\" franchise, what is the name of Goku's brother?",
        "possibleAnswers": [
            "Gohan",
            "Vegeta",
            "Bardock",
            "Raditz"
        ]
    }

```

The checkanswer endpoint expects an Answer body. An example of a valid answer would be: 
```json
{ 
"id": 0, 
"answer": "16" 
}
```

Upon making a call to this endpoint, a Response object will be returned. A Response looks like:
```json
{
    "id": 4,
    "response": "Incorrect Answer",
    "is_correct": false
}
```
