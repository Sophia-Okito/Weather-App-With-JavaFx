# JavaFX Weather Application

A simple weather application built with JavaFX that fetches and displays real-time weather data for a specified city. This app connects to the OpenWeather API to retrieve weather information and presents it in a user-friendly table format. It also features dynamic background images based on temperature and icon-based weather condition indicators.

## Features

- Search weather by city name
- Display current weather details (e.g., temperature, humidity, wind speed, description)
- Dynamically update background image based on temperature
- Display weather icons in a table view for quick visual reference
- Lightweight popup window for additional weather details

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [File Structure](#file-structure)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)
- [Credits](#credits)

---

## Installation

### Prerequisites
- Java 11 or higher
- JavaFX 17 or higher
- OpenWeather API Key (you can obtain one by signing up at [OpenWeather](https://openweathermap.org))

### Steps

1. Clone the repository.
    ```bash
    git clone [https://github.com/your-username/your-repo-name.git](https://github.com/Sophia-Okito/Weather-App-With-JavaFx.git)
    ```

2. Import the project into your preferred IDE (e.g., IntelliJ IDEA or Eclipse).

3. Add JavaFX SDK to the project:
   - Download JavaFX SDK from [JavaFX Downloads](https://gluonhq.com/products/javafx/).
   - Add JavaFX libraries to your project settings.

4. Configure the API Key:
   - Open `HomeController.java` and replace `API_KEY` with your OpenWeather API key.
   ```java
   private static final String API_KEY = "your_openweather_api_key";
   ```

5. Run the application from your IDE.

## Usage

1. Enter the city name in the search bar and click "Search" to fetch weather data.
2. The table will display information including:
    - City name
    - Temperature
    - Weather description
    - Humidity, wind speed, cloudiness, and more
3. Click on a row to open a popup with additional details.
4. The background image changes based on temperature conditions.

## File Structure

```plaintext
src/
│
├── main/
│   ├── java/
│   │   ├── com.example.week7/
│   │   │   ├── controllers/
│   │   │   │   └── HomeController.java  # Main controller handling search and API requests
│   │   │   ├── models/
│   │   │   │   └── Search.java           # Model representing weather search data
│   │   │   └── HelloApplication.java     # Main application entry point
│   └── resources/
│       ├── com.example.week7.images/     # Folder containing weather icons and background images
│       └── com.example.week7.styles/     # CSS styles
│           └── style.css                 # Main stylesheet
```

## Dependencies

- **JavaFX** - For building the GUI.
- **JSON** - To parse data from OpenWeather API.
- **OpenWeather API** - Source of weather data.

## Screenshots

<!-- Add screenshots here -->
Example screenshot showing the app’s main view.

<img width="1201" alt="Screenshot 2024-11-01 at 03 35 40" src="https://github.com/user-attachments/assets/c7559f3c-c9ec-4a90-a709-76797e43d785">


<img width="395" alt="Screenshot 2024-11-01 at 03 46 42" src="https://github.com/user-attachments/assets/542693c0-8a0a-4744-a9fd-9f14edfb523d">


<img width="1194" alt="Screenshot 2024-11-01 at 03 50 36" src="https://github.com/user-attachments/assets/7c221814-fe6d-4d7f-8a92-0f29bd3cd064">




## Credits

This application uses the OpenWeather API for weather data and JavaFX for the graphical interface.

### License
[MIT License](LICENSE)


This `README.md` provides a comprehensive overview, setup instructions, and the structure of your JavaFX project, which will help users or collaborators understand, install, and use the application effectively. You can also add more details if you add any new features or make further customizations.
