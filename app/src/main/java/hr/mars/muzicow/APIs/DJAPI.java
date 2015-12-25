package hr.mars.muzicow.APIs;

import java.util.List;

import hr.mars.muzicow.Models.DJ;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by mars on 25/12/15.
 */
public interface DJAPI {
    @GET("/api/{email}")
    void getDJ(@Path(value="email", encode=false) String st, Callback<List<DJ>> cb);


    @FormUrlEncoded
    @POST("/api/djs/{id}")
    void updateDJ(@Path(value="id", encode = false) String url, @Field("_ID") String ID,
                  @Field("name") String name, @Field("website") String website,
                  @Field("country") String country, @Field("city") String city,
                  @Field("email") String email, @Field("nickname") String nickname,
                  Callback<List<DJ>> cb);

    @GET("/api/djs?filter=%7B%22where%22%3A%7B%22email%22%3A%22hrorejas@foi.hr%22%7D%7D")
    void getDJ( Callback<List<DJ>> cb);
}
