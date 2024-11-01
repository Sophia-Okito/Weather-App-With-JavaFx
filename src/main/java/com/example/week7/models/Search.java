package com.example.week7.models;

import com.example.week7.HelloApplication;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Search {

    public static final List<Search> searchHistory = new ArrayList<>();

    private StringProperty city;
    private StringProperty day;
    private SimpleDoubleProperty temperature;
    private StringProperty icon;
    private StringProperty description;
    private StringProperty windSpeed;
    private StringProperty cloudiness;
    private StringProperty pressure;
    private StringProperty humidity;

    private ImageView iconImage;

    static {
        Search.load();
    }


    public Search(String city, String day, Double temperature,
                  String icon, String description, String windSpeed,
                  String cloudiness, String pressure, String humidity) {
        this.city = new SimpleStringProperty(city);
        this.day = new SimpleStringProperty(day);
        this.temperature = new SimpleDoubleProperty(temperature);
        this.icon = new SimpleStringProperty(icon);
        this.description = new SimpleStringProperty(description);
        this.windSpeed = new SimpleStringProperty(windSpeed);
        this.cloudiness = new SimpleStringProperty(cloudiness);
        this.pressure = new SimpleStringProperty(pressure);
        this.humidity = new SimpleStringProperty(humidity);
        URL imageUrl = getBackgroundImageBasedOnTemperature(temperature);
        Image image = new Image(imageUrl.toString());
        this.iconImage = new ImageView(image);    }

    public Search() {
    }
    public static void load() {
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public double getTemperature() {
        return temperature.get();
    }

    public SimpleDoubleProperty temperatureProperty() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature.set(temperature);
    }

    public String getIcon() {
        return icon.get();
    }

    public StringProperty iconProperty() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon.set(icon);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getWindSpeed() {
        return windSpeed.get();
    }

    public StringProperty windSpeedProperty() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed.set(windSpeed);
    }

    public String getCloudiness() {
        return cloudiness.get();
    }

    public StringProperty cloudinessProperty() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness.set(cloudiness);
    }

    public String getPressure() {
        return pressure.get();
    }

    public StringProperty pressureProperty() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure.set(pressure);
    }

    public String getHumidity() {
        return humidity.get();
    }

    public StringProperty humidityProperty() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity.set(humidity);
    }

    public ImageView getIconImage() {
        return iconImage;
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



}
