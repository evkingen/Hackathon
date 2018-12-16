package msk.android.academy.javatemplate.data.network;

import io.reactivex.Single;
import msk.android.academy.javatemplate.data.model.MarkersDTO;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarkersEndPoint {

//    @GET("large")
//    Single<MarkersDTO> search(@Query("id") int id);

    @GET("set")
    Single<JsonObject> set(
            @Query("idOwner") int idOwner,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("title") String title,
            @Query("fullText") String fullText,
            @Query("imageUrl") String imageUrl
    );

    @GET("large")
    Single<MarkersDTO> search_target(@Query("id") int id);
}
