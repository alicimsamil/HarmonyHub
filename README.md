## Key Features
- Allows you to view tracks.
- You can listen to previews of the tracks.
- You can use it in day or night mode thanks to the correctly defined theme.

## Architecture
MVVM (Model-View-ViewModel) with Clean Architecture principles in this project. 
This architectural approach provides independence, scalability and testability
by separating the different layers of the project.

### Layers
The project is divided into the following layers:

- **Data**: Access to data sources, network requests and data storage operations are performed in this layer. This layer works independently of the data sources, providing the retrieval and storage of data.
- **Domain**: Business logic and data processing operations take place in this layer. The operations required to access the data (querying, transforming, filtering etc.) are defined here.
- **Presentation**: User interface related operations take place in this layer. Data retrieval and display, user interactions, and UI feedback are managed in this layer. Communication is provided with ViewModels, which minimizes dependency on Views.

## Libraries
- **Material Design** - UI design
- **AndroidX** - ViewModel, LiveData, Splash Screen API, Paging3
- **KotlinX** - Coroutines, Flow, StateFlow, Serialization
- **Hilt** -  Dependency Injection
- **Navigation Component** - User navigation
- **Glide** - Loading Images
- **Room** - Database Storage
- **Retrofit** - Network requests
- **OkHttp-Chucker** - Manage and monitor HTTP requests


### Testing Libraries:
- **JUnit4** - Unit testing framework

## Screenshots


https://github.com/alicimsamil/HarmonyHub/assets/81926983/a562fcbe-7f43-456a-be89-b456bc2eb5e3


https://github.com/alicimsamil/HarmonyHub/assets/81926983/17fc8330-68cf-4094-9f8f-f370a5ad11d6


https://github.com/alicimsamil/HarmonyHub/assets/81926983/6987b8a1-18e0-4360-b232-0220e8a37fb0


https://github.com/alicimsamil/HarmonyHub/assets/81926983/2bc37db4-4773-4e9f-a64d-1594dfa49a8e

![Screenshot 2023-06-05 at 15 24 50](https://github.com/alicimsamil/HarmonyHub/assets/81926983/2755042f-ca63-4220-b9c6-a6763790caae)
![Screenshot 2023-06-05 at 15 05 47](https://github.com/alicimsamil/HarmonyHub/assets/81926983/939ed13f-e739-4c81-95a9-765295db62b6)
