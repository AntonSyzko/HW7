package com.example.hw7;

import android.graphics.Bitmap;

/**
 * Created by Ыг on 28.09.2015.
 */
public class AdvDBTableObject {

    //string   values for  my table and columns
    public static final String TABLE_NAME = "Advertisements";

    public static final String ID_COLUMN_KEY = "id";
    public static final String PLACE_COLUMN_KEY = "place";
    public static final String DATE_COLUMN_KEY = "date";
    public static final String TIME_COLUMN_KEY = "time";
    public static final String IMAGE_COLUMN_KEY = "image";
    public static final String DISTRICT_COLUMN_KEY = "district";

    //ultimate array of column keys
    public static final String[] COLUMNS_ARRAY = {ID_COLUMN_KEY, PLACE_COLUMN_KEY, DATE_COLUMN_KEY, TIME_COLUMN_KEY, DISTRICT_COLUMN_KEY, IMAGE_COLUMN_KEY};

    //the very columns objects values - entities kind of - abive are just string keys indicators
    private int id;
    private String place;
    private String date;
    private String time;
    private String district;
    private Bitmap image;

    public AdvDBTableObject() {
    }




    public AdvDBTableObject( String place, String date, String time, String district, Bitmap image) {

        this.id = id;
        this.place = place;
        this.date = date;
        this.time = time;
        this.district = district;
        this.image = image;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvDBTableObject that = (AdvDBTableObject) o;

        return place.equals(that.place);

    }

    @Override
    public int hashCode() {
        return place.hashCode();
    }


    @Override
    public String toString() {
        return "AdvDBTableObject{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", district='" + district + '\'' +
                ", image=" + image +
                '}';
    }
}

