package hr.mars.muzicow.RESTful.api;

import java.util.List;

import hr.mars.muzicow.RESTful.model.DJ;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Emil on 21.12.2015..
 */
public interface API {

    @GET("/api/djs")
    Call<List<DJ>> getDJ();

    @POST("/api/djs/update")
    Call<List<DJ>> updateDJ();



    //Call<JSONObject> slanjeEventa(@Field("_ID") String ID);
    /*
    Call<String> slanjeEventa(@Field("_ID") String ID, @Field("name") String name, @Field("website") String website
        ,@Field("country") String country, @Field("city") String city,@Field("nickname") String nickname);
*/
}
