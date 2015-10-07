package com.example.hw7;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ыг on 01.10.2015.
 */
public class ListItemObject implements Parcelable {
    private String place;
    private String date_time;
    private String district;
    private Bitmap image;

    public ListItemObject(String place, String date_time, String district, Bitmap image) {
        this.place = place;
        this.date_time = date_time;
        this.district = district;
        this.image = image;
    }

    public ListItemObject() {
    }

    protected ListItemObject(Parcel in) {
        place = in.readString();
        date_time = in.readString();
        district = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<ListItemObject> CREATOR = new Creator<ListItemObject>() {
        @Override
        public ListItemObject createFromParcel(Parcel in) {
            return new ListItemObject(in);
        }

        @Override
        public ListItemObject[] newArray(int size) {
            return new ListItemObject[size];
        }
    };

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ListItemObject{" +
                "place='" + place + '\'' +
                ", date_time='" + date_time + '\'' +
                ", district='" + district + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItemObject that = (ListItemObject) o;

        return place.equals(that.place);

    }

    @Override
    public int hashCode() {
        return place.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(place);
        dest.writeString(date_time);
        dest.writeString(district);
        dest.writeParcelable(image, flags);
    }

    public ListItemObject[] newArray(int size){
        return new ListItemObject[size];
    }
}
