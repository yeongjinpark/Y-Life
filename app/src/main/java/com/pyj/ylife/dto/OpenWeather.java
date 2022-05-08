package com.pyj.ylife.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeather {
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("rain")
    private Rain rain;
    @SerializedName("snow")
    private Snow snow;
    @SerializedName("sys")
    private Sys sys;

    @SerializedName("visibility")
    private int visibility;
    @SerializedName("dt")
    private long dt;
    @SerializedName("timezone")
    private int timezone;
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public Sys getSys() {
        return sys;
    }

    public int getVisibility() {
        return visibility;
    }

    public long getDt() {
        return dt;
    }

    public int getTimezone() {
        return timezone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "OpenWeather{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base=" + base +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", rain=" + rain +
                ", snow=" + snow +
                ", sys=" + sys +
                ", visibility=" + visibility +
                ", dt=" + dt +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name=" + name +
                ", cod=" + cod +
                '}';
    }
}
