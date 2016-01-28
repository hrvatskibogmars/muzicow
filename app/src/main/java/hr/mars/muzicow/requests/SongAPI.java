package hr.mars.muzicow.requests;

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.models.Song;
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
                    @Field("status") String status, @Field("upvoted") String upvited, @Field("voted[]") String voted,
                    @Field("youtube_link") String youtube_link,
                    @Field("name") String name,
                    Callback<Response> cb);


    @GET("/api/{event_ID}")
    void getEvent(@Path(value = "event_ID", encode = false) String st, Callback<List<Event>> cb);

    @GET("/api/{songs}")
    void getSongs(@Path(value = "songs", encode = false) String st, Callback<List<Song>> cb);


    @GET("/api/{status}")
    void getStatus(@Path(value = "status", encode = false) String st, Callback<List<Song>> cb);

    @FormUrlEncoded
    @POST("/api/songs/{url}")
    void updateSong(@Path(value = "url", encode = false) String url,
                    @Field("status") String status,
                    Callback<List<Song>> cb);

    @FormUrlEncoded
    @POST("/api/songs/{url}")
    void updateUpvote(@Path(value = "url", encode = false) String url,
                      @Field("upvoted") String upvoited, @Field("voted[]") ArrayList<String> voted,
                      Callback<Song> cb);


}