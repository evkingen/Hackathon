package msk.android.academy.javatemplate.data.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestApi {
    private static final String URL = "http://192.168.4.183:8080/";
    private static final int TIMEOUT_SECONDS = 2;
    private static RestApi restApi;
    private final MarkersEndPoint endPoint;

    private RestApi() {
        final OkHttpClient client = createOkHttpClient();
        final Retrofit retrofit = createRetrofitBuilder(client);
        endPoint = retrofit.create(MarkersEndPoint.class);
    }

    public static synchronized RestApi getInstance() {
        if (restApi == null)
            restApi = new RestApi();
        return restApi;
    }

    @NonNull
    private Retrofit createRetrofitBuilder(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MarkersEndPoint getEndPoint() {
        return endPoint;
    }

    @NonNull
    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .build();
    }
}