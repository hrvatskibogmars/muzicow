package hr.mars.muzicow.RESTful.api;

import java.util.List;
import java.util.Map;

import hr.mars.muzicow.RESTful.model.DJ;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.Url;

/**
 * Created by Emil on 21.12.2015..
 */
public interface API {

    //Get all DJs
    //@GET("/api/djs")
    //Call<List<DJ>> getDJ();


    //Get dj by email
    //@GET ("/api/djs?filter=%7B%22where%22%3A%7B%22email%22%3A%22string%22%7D%7D")
    //Call<List<DJ>> getDJ();

    //Get dj by email
    @GET
    Call<List<DJ>> getDJ(@Url String emil);


    //Update DJ INFO
    @FormUrlEncoded
    @POST
    Call<List<DJ>> updateDJ(@Url String url, @Field("_ID") String ID, @Field("name") String name, @Field("website") String website
    ,@Field("country") String country, @Field("city") String city,@Field("nickname") String nickname, @Field("email") String email);


    //Call<JSONObject> slanjeEventa(@Field("_ID") String ID);
    /*
    Call<String> slanjeEventa(@Field("_ID") String ID, @Field("name") String name, @Field("website") String website
        ,@Field("country") String country, @Field("city") String city,@Field("nickname") String nickname);
*/
}
