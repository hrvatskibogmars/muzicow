package hr.mars.muzicow.Interface;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Emil on 21.12.2015..
 */
public interface eventAPI {
    @FormUrlEncoded
    @POST(":3000/api/djs/findOne")
    Call<String> slanjeEventa(@Field("event") String event, @Field("time") String time, @Field("genre") String genre
        ,@Field("latitude") String latitude, @Field("longitude") String longitude);

}
