package com.example.week7.controllers;

import com.example.week7.HelloApplication;
import com.example.week7.models.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeController {

    private static final String API_KEY = "";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @FXML
    private TextField cityTextField;
    @FXML
    private TableColumn<Search, String> city;
    @FXML
    private TableColumn<Search, String> day;
    @FXML
    private TableColumn<Search, Integer> temperature;
    @FXML
    private TableColumn<Search, ImageView> icon;
    @FXML
    private TableColumn<Search, String> description;
    @FXML
    private TableColumn<Search, String> windSpeed;
    @FXML
    private TableColumn<Search, String> cloudiness;
    @FXML
    private TableColumn<Search, String> pressure;
    @FXML
    private TableColumn<Search, String> humidity;
    @FXML
    private Button searchButton;
    @FXML
    private Button quitButton;
    @FXML
    private TableView<Search> searchTable;

    @FXML
    private void initialize() {
        // Bind columns to Search properties
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        day.setCellValueFactory(new PropertyValueFactory<>("day"));
        temperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));

        // Setting the icon column with an ImageView
        icon.setCellValueFactory(new PropertyValueFactory<>("iconImage"));
        icon.setCellFactory(col -> new TableCell<Search, ImageView>() {
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    item.setFitWidth(50); // Set desired width
                    item.setFitHeight(50); // Set desired height
                }
            }
        });

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        windSpeed.setCellValueFactory(new PropertyValueFactory<>("windSpeed"));
        cloudiness.setCellValueFactory(new PropertyValueFactory<>("cloudiness"));
        pressure.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        humidity.setCellValueFactory(new PropertyValueFactory<>("humidity"));

        // Set action for buttons
        searchButton.setOnAction(event -> performSearch());
        quitButton.setOnAction(event -> HelloApplication.quitApplication());

        loadSearchData();
    }

    private void loadSearchData() {
        try {
            List<Search> searches = Search.searchHistory;
            ObservableList<Search> observableSearches = FXCollections.observableArrayList(searches);
            searchTable.setItems(observableSearches);
        } catch (Exception e) {
            showErrorAlert("Error loading history", "An error occurred while loading data.");
        }
    }

    // Action for search functionality
    private void performSearch() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            fetchWeatherData(city);
        } else {
            showErrorAlert("Invalid Input", "Please enter a city name.");
        }
    }

    private void fetchWeatherData(String cityName) {
        try {

            JSONObject json;

            try {
                json = readJsonFromUrl(BASE_URL + "?q=" + cityName + "&appid=" + API_KEY);
            } catch (IOException e) {
                return;
            }
            String temperature = json.getJSONObject("main").get("temp").toString();
            String temperatureInCelsius = String.format("%.2f", Double.parseDouble(temperature) - 273.15);

            String pressure = json.getJSONObject("main").get("pressure").toString();
            String humidity = json.getJSONObject("main").get("humidity").toString();
            String windSpeed = json.getJSONObject("wind").get("speed").toString();
            String cloudiness = json.getJSONObject("clouds").get("all").toString();
            String description = json.getJSONArray("weather").getJSONObject(0).get("description").toString();
            String icon = json.getJSONArray("weather").getJSONObject(0).get("icon").toString();
            String city = json.get("name").toString();
            String day = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
            Search search = new Search(city, day, Double.parseDouble(temperatureInCelsius), icon, description, windSpeed, cloudiness, pressure, humidity);
            searchTable.getItems().add(search);
            Search.searchHistory.add(search);
            showWeatherPopup(search);

        } catch (Exception e) {
            showErrorAlert("Error", "An error occurred while fetching the weather data.");
            e.printStackTrace();
        }
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private void showWeatherPopup(Search search) {
        Stage popupStage = new Stage();
        VBox layout = new VBox(10);

        // Set the background based on temperature
        URL backgroundImage = getBackgroundImageBasedOnTemperature(search.getTemperature());
        layout.setStyle("-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-padding: 20;");

        // Create Labels with padding
        Label cityLabel = new Label("City: " + search.getCity());
        cityLabel.setStyle("-fx-padding: 10; -fx-font-size: 14px;");

        Label temperatureLabel = new Label("Temperature: " + String.format("%.2f °C", search.getTemperature()));
        temperatureLabel.setStyle("-fx-padding: 10; -fx-font-size: 14px;");

        Label descriptionLabel = new Label("Description: " + search.getDescription());
        descriptionLabel.setStyle("-fx-padding: 10; -fx-font-size: 14px;");

        Label humidityLabel = new Label("Humidity: " + search.getHumidity() + "%");
        humidityLabel.setStyle("-fx-padding: 10; -fx-font-size: 14px;");

        Label windSpeedLabel = new Label("Wind Speed: " + search.getWindSpeed() + " m/s");
        windSpeedLabel.setStyle("-fx-padding: 10; -fx-font-size: 14px;");

        // Add information to the layout
        layout.getChildren().addAll(cityLabel, temperatureLabel, descriptionLabel, humidityLabel, windSpeedLabel);

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300); // Increased size
        popupStage.setScene(scene);
        popupStage.setTitle("Weather Information");
        popupStage.show();


    }

    private URL getBackgroundImageBasedOnTemperature(double temperature) {
        String imagePath;

        if (temperature < 0) {
            imagePath = "/com/example/week7/images/13.png";
        } else if (temperature < 15) {
            imagePath = "/com/example/week7/images/11.png";
        } else if (temperature < 30) {
            imagePath = "/com/example/week7/images/02d.png";
        } else {
            imagePath = "/com/example/week7/images/01d.png";
        }

        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }
        return imageUrl;
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
