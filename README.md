# Documentation

### Specs
- You need to have gradle 8.5+ and Java 21 in order to use the application


### Techstack
- Built by Kotlin and Java21
- Package Manager Gradle
- Spring Boot and Web


### Commands

    - ./gradlew build: build the application and run the test
    - ./gradlew test: run the tests 4 unit test 1 integration tests
    - ./gradlew bootRun: Run the application


### API

Method: GET
URL: /evalutaion
Parameters:
- urls: String[]: Takes given CSV format urls and tries to download them
- year: Int?: The year you want to filter to find speaker at most speech: Default is 2013
- topic: String?: The topic you want to filter to find speaker at most speech: Default is "homeland security"


Response Object:

    {
      "mostSpeeches": null,
      "mostSecurity": "Alexander Abel",
      "leastWordy": "Caesare Collins"
    }


