## Key Features
- Allows you to view tracks
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

https://github.com/alicimsamil/HarmonyHub/assets/81926983/8c7be63a-b6d8-4f66-8914-070435b13f42

https://github.com/alicimsamil/HarmonyHub/assets/81926983/a573f167-0708-4693-90d3-71b84d42d268

a-1b19-40c4-bae4-6be866a85b7f)


https://github.com/alicimsamil/HarmonyHub/assets/81926983/2a3eb76c-97b5-4fd6-91c6-31d4d2c5daaa


https://github.com/alicimsamil/HarmonyHub/assets/81926983/eb0af6fc-0519-4a5d-9b58-2c7fb1ce5905


![Screenshot 2023-06-05 at 15 05 47](https://github.com/alicimsamil/HarmonyHub/assets/81926983/2cbf1e62-5f57-4fdf-81f6-8a1fcc3a8f86)
![Screenshot 2023-06-05 at 15 24 50](https://github.com/alicimsamil/HarmonyHub/assets/81926983/62132bc

