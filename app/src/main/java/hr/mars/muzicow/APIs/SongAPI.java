package hr.mars.muzicow.APIs;

import java.util.List;

import hr.mars.muzicow.Models.DJ;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Emil on 16.1.2016..
 */
public interface SongAPI {
    @FormUrlEncoded
    @POST("/api/songs/")
    void createSong(@Field("artist") String artist,
                     @Field("event_id") String event_id, @Field("description") String description,
                     @Field("status") String status, @Field("youtube_link") String youtube_link,
                     @Field("name") String name,
                     Callback<Response> cb);


    @GET("/api/{event_ID}")
    void getSong(@Path(value="event_ID", encode=false) String st, Callback<List<DJ>> cb);
}
