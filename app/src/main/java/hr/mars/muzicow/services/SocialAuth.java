package hr.mars.muzicow.services;

import android.content.Context;

/**
 * Created by mars on 27/01/16.
 */
public interface SocialAuth<ObjectLogin, T2, T3> {
    void setProvider(String provider);
    String getProvider();

    void setKey(String key);
    String getKey();

    void setSecret(String key);
    String getSecret();

    boolean save(ObjectLogin object_login); // should serialize and save data to global cache, returns error code
    ObjectLogin load(); // reads from global cache and returns the ObjectLogin

    void setRole(String role);
    String getRole();
    boolean login();  // method retrieves oauth access_token from provider
    boolean logout(); // destroys the access_token from local cache
    boolean signup(Context context); // tries to retrieve user data from provider and saves it to database
}


