package hr.mars.muzicow.requests;

import java.util.List;

import hr.mars.muzicow.models.DJ;
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
    @GET("/api/{ID}")
    void getDJ(@Path(value = "ID", encode = false) String st, Callback<List<DJ>> cb);


    @FormUrlEncoded
    @POST("/api/djs/")
    void createDJ(@Field("_ID") String ID,
                  @Field("name") String name, @Field("website") String website,
                  @Field("location") String location, @Field("nickname") String nickname,
                  @Field("profile_url") String profile_url, @Field("twitter_url") String twitter_url,
                  @Field("description") String description,
                  Callback<List<DJ>> cb);

    @FormUrlEncoded
    @POST("/api/djs/{url}")
    void updateDJ(@Path(value = "url", encode = false) String url, @Field("_ID") String ID,
                  @Field("name") String name, @Field("website") String website,
                  @Field("location") String location, @Field("nickname") String nickname,
                  @Field("profile_url") String profile_url, @Field("twitter_url") String twitter_url,
                  @Field("description") String description,
                  Callback<List<DJ>> cb);
}
