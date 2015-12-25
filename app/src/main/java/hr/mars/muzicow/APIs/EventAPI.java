package hr.mars.muzicow.APIs;

import java.util.List;

import hr.mars.muzicow.Models.DJ;

import hr.mars.muzicow.Models.Event;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Emil on 21.12.2015..
 */
public interface EventAPI {
    //Create event
    @FormUrlEncoded
    @POST("/api/events/")
    void createEvent(@Field("dj_ID") String djID, @Field("latitude") String latitude,
                  @Field("longitude") String longitude, @Field("genre") String genre,
                     @Field("status") String status, @Field("name") String name,
                     Callback<List<Event>> cb);

    //Check for active event - if active get event
    @GET("/api/{event_active}")
    void getActiveEvent(@Path(value="event_active", encode=false) String ea, Callback<List<Event>> ev);

    //Update event
    @POST("/api/events/{where}")
    void updateEvent(@Field("dj_ID") String djID, @Field("latitude") String latitude,
                     @Field("longitude") String longitude, @Field("genre") String genre,
                     @Field("status") String status, @Field("name") String name,
                     Callback<List<Event>> cb);

}

