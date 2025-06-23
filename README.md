
# Fetch App
Fetch - Fetching the best dog breed images from the internet 

## Architecture and Design
The app is built with a lightweight clean architecture style (avoiding unnecessary complexity where possible), using MVVM for the presentation layer, and repositories for the data layer.
The app is split into multiple modules:
- app - entry point of app - sets up DI
- breed-list - the code to display the list view for breeds
- breed-detail - the code to display the detail view for breeds
- networking - houses the network client and related code


## Testing 
The app uses a mixture of testing libraries and techniques to ensure it has a high amount of overall coverage.
- Unit testing for everything where possible
- Snapshot testing to ensure all screens and components don't change accidentally 
- Compose UI tests to ensure the full journey works together   

## Library Choices
- Ktor - networking client
- KotlinxSerialization - JSON serialisation
- Coil - Image loading
- Koin - DI - simpler/quicker option for DI than dagger - however can cause runtime exceptions
- JUnit4 - unit testing
- Mockk - mocking
- test-paramater-injector - paramaterise unit tests - allowing to test multiple combinations easily