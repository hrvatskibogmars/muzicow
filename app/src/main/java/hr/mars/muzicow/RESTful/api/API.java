package hr.mars.muzicow.RESTful.api;

import java.util.List;
import java.util.Map;

import hr.mars.muzicow.RESTful.model.DJ;

import hr.mars.muzicow.RESTful.model.Event;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by Emil on 21.12.2015..
 */
public interface API {

    @GET("/api/{email}")
    void getDJ(@Path(value="email", encode=false) String st, Callback<List<DJ>> cb);


    @FormUrlEncoded
    @POST("/api/djs/{id}")
    void updateDJ(@Path(value="id", encode = false) String url, @Field("_ID") String ID,
                  @Field("name") String name, @Field("website") String website,
                  @Field("country") String country, @Field("city") String city,
                  @Field("email") String email, @Field("nickname") String nickname,
                  Callback<List<DJ>> cb);


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

