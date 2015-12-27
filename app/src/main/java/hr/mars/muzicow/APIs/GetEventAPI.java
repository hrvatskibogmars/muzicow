package hr.mars.muzicow.APIs;

import java.util.List;


import hr.mars.muzicow.Models.Event;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Emil on 27.12.2015..
 */
public interface GetEventAPI {
    @GET("/api/{event_active}")
    void getEvent(@Path(value="event_active", encode=false) String ea, Callback<List<Event>> cb);
}
