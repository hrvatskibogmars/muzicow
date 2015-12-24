package hr.mars.muzicow.RESTful;

import com.squareup.okhttp.OkHttpClient;


import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Created by Emil on 21.12.2015..
 */


public class ServiceGenerator {

    public static final String API_BASE_URL = "http://46.101.121.184:3000";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
