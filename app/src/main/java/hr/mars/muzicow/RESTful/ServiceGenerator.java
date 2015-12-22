package hr.mars.muzicow.RESTful;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Emil on 21.12.2015..
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://46.101.121.184:3000";

    private static OkHttpClient httpClient = new OkHttpClient();
    //httpClient.interceptors().add(new LoggingInterceptor());

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
