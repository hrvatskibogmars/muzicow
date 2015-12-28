package hr.mars.muzicow.Services;

import java.util.List;

import hr.mars.muzicow.Models.DJ;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Emil on 28.12.2015..
 */
public interface ImageListener {

        public void success(List<DJ> djOb);
        public void failure(RetrofitError e);
    }


