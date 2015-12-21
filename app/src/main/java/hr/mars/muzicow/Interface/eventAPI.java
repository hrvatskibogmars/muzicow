package hr.mars.muzicow.Interface;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Emil on 21.12.2015..
 */
public interface eventAPI {

    @GET("/api/djs")
    Call<String> slanjeEventa();
    /*
    Call<String> slanjeEventa(@Field("_ID") String ID, @Field("name") String name, @Field("website") String website
        ,@Field("country") String country, @Field("city") String city,@Field("nickname") String nickname);
*/
}
