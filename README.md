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
        "id": 0,
        "questionText": "Whistler was the codename of this Microsoft Operating System.",
        "possibleAnswers": [
            "Windows 2000",
            "Windows 95",
            "Windows 7",
            "Windows XP"
        ]
}

```

The checkanswer endpoint expects an Answer body. An example of a valid answer would be: 
```json
{ 
"id": 0, 
"answer": "Windows XP" 
}
```

Upon making a call to this endpoint, a Response object will be returned. A Response looks like:
```json
{
    "id": 0,
    "response": "Windows XP",
    "is_correct": false
}
```
