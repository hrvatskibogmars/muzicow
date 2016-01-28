package hr.mars.muzicow.requests;

import java.util.List;

import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Event;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by mars on 25/12/15.
 */
public interface UserAPI {

    @GET("/api/{ID}")
    void getDJ(@Path(value = "ID", encode = false) String st, Callback<List<DJ>> cb);

    @GET("/api/{event_active}")
    void getEvent(@Path(value = "event_active", encode = false) String ea, Callback<List<Event>> cb);
}
