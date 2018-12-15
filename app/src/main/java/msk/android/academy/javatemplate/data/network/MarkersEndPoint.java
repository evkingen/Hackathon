package msk.android.academy.javatemplate.data.network;

import io.reactivex.Single;
import msk.android.academy.javatemplate.data.model.MarkersDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarkersEndPoint {

    @GET("large")
    Single<MarkersDTO> search(@Query("id") int id);
}
