package com.log.koronatakip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpesificData {

    @SerializedName("Confirmed")
    @Expose
    private int Confirmed;

    @SerializedName("Active")
    @Expose
    private int Active;

    @SerializedName("Recovered")
    @Expose
    private int Recovered;

    @SerializedName("Deaths")
    @Expose
    private int Deaths;

    @SerializedName("Date")
    @Expose
    private String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int confirmed) {
        Confirmed = confirmed;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public int getRecovered() {
        return Recovered;
    }

    public void setRecovered(int recovered) {
        Recovered = recovered;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int deaths) {
        Deaths = deaths;
    }
}
