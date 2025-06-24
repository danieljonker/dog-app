# Fetch App
Fetch - Fetching the best dog breed images from the internet 

## Architecture and Design
The app is built with a lightweight clean architecture style (avoiding unnecessary complexity where possible), using MVVM for the presentation layer, the repository pattern to fetch the data and map it, and ktor for the networking.
The app is split into multiple modules:
- app - entry point of app - sets up DI and Nav via the MainActivity
- breedlist - displays a list of breeds
- breedview - displays images of a specific breed in a horizontal pager
- networking - houses the network client and routes

## Testing 
The app uses a mixture of testing libraries and techniques to ensure it has a high amount of overall coverage.
- Unit testing for everything where possible
- Snapshot testing (using paparazzi) to ensure all screens and components don't change accidentally 
- Compose UI tests to ensure the journeys work together flawlessly   

## Library Choices
- Ktor - networking client
- KotlinxSerialization - JSON serialisation
- Coil - Image loading
- Koin - DI - simpler/quicker option for DI than dagger/hilt - however can cause runtime exceptions
- JUnit4 - unit testing
- Mockk - mocking library
- test-paramater-injector - paramaterise unit tests - allowing to test multiple combinations easily
- Paparazzi - snapshot image tool - note I've not pushed the snapshots to github for this, you need to record them first


## Running the app/tests
To run the app, you can use Android Studio, or install from terminal using `./gradlew installDebug`
To run the tests you can run them from the gradle menu inside Android Studio or with `./gradlew testDebugUnitTest`
To run the snapshots tests you can run the from the gradle menu or with `./gradlew recordPaparazziDebug` and `./gradlew verifyPaparazziDebug`

## Image caching 
For image loading, I've used coil with the standard coil setup, I've included the out of the box disk cache setup which can be seen in FetchApplication

## Future considerations
I didn't have a lot of time to work on this due to other commitments, so couldn't quite squeeze everything I'd like in.

With more time I'd have made the following additions:
- Add loading and error states into the UI
- More thorough testing, including full journey UI tests to validate the navigation and the paging of images
- More polished UI, adding some branding, some animations and transitions
- Koin tests to validate the DI graph
