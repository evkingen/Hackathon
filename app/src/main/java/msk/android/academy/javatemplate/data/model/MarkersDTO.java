package msk.android.academy.javatemplate.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MarkersDTO implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("coordinates")
    private CoordinatesDTO coordinatesDTO;

    @SerializedName("ownerId")
    private int ownerId;

    @SerializedName("title")
    private String title;

    @SerializedName("fullText")
    private String fullText;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("date")
    private String date;

    @SerializedName("favorite")
    private boolean favorite;

    public String getFullText() {
        return fullText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }


    public boolean isFavorite() {
        return favorite;
    }

    public String getTitle() {
        return title;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public CoordinatesDTO getCoordinatesDTO() {
        return coordinatesDTO;
    }

    public int getId() {
        return id;
    }
}
