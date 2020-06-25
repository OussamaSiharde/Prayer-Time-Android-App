package com.journaldev.customlistview;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataModel {

    String date;
    String fajr;
    String sunrise;
    String dhuhr;
    String asr;
    String maghrib;
    String isha ;

    public String getDate() {
        return date;
    }
    public String getFajr() {
        return fajr;
    }
    public String getSunrise() {
        return sunrise;
    }

    public String getDhuhr() {
        return dhuhr;
    }
    public String getAsr() {
        return asr;
    }

    public String getMaghrib() {
        return maghrib;
    }
    public String getIsha() {
        return isha;
    }



    public DataModel(String date, String fajr, String sunrise, String dhuhr, String asr, String maghrib, String isha) {
        this.date = date;
        this.fajr = fajr;
        this.sunrise = sunrise;
        this.dhuhr = dhuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
    }
}
