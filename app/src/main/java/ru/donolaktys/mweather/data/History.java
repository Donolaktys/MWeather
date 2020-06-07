package ru.donolaktys.mweather.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "CITY")
    private String city;
    @ColumnInfo(name = "IMAGE")
    private String image;
    @ColumnInfo(name = "TEMPERATURE")
    private String temperature;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public History Copy(long id) {
        History newHistory = new History();
        newHistory.setTemperature(this.getTemperature());
        newHistory.setImage(this.getImage());
        newHistory.setCity(this.getCity());
        newHistory.setId(id);
        return newHistory;
    }
}

