package hr.mars.muzicow.Interface;

import org.json.JSONObject;

import java.util.List;

import hr.mars.muzicow.DJAtributes;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Emil on 21.12.2015..
 */
public interface eventAPI {

    @GET("/api/djs")
    Call<List<DJAtributes>> getDJ();

    //Call<JSONObject> slanjeEventa(@Field("_ID") String ID);
    /*
    Call<String> slanjeEventa(@Field("_ID") String ID, @Field("name") String name, @Field("website") String website
        ,@Field("country") String country, @Field("city") String city,@Field("nickname") String nickname);
*/
}
