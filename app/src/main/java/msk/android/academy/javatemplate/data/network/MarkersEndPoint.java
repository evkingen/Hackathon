package msk.android.academy.javatemplate.data.network;

import java.util.List;

import io.reactivex.Single;
import msk.android.academy.javatemplate.data.model.MarkersDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarkersEndPoint {


    @GET("all")
    Single<List<MarkersDTO>> search_all();

    @GET("large")
    Single<MarkersDTO> search_target(@Query("id") int id);
}
