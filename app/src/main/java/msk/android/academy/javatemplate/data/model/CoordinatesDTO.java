package msk.android.academy.javatemplate.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoordinatesDTO implements Serializable {
    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
