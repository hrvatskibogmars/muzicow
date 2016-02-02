package hr.mars.muzicow.utils;

/**
 * Created by mars on 27/01/16.
 */
public interface SocialAuth<LoginObject, ContextObject, SocialObject> {
    ContextObject getContext();

    /***
     * SocialAuth interface for implementing social authentication from different providers in
     * the MusicCow app.
     *
     * @param ctx
     */
    void setContext(ContextObject ctx);

    void setSocialObject(SocialObject socialButton);

    SocialObject getsocialObject();

    String getProvider();

    void setProvider(String provider);

    String getKey();

    void setKey(String key);

    String getSecret();

    void setSecret(String key);

    void setup();

    boolean save(LoginObject object_login); // should serialize and save data to global cache, returns error code

    LoginObject load(); // reads from global cache and returns the ObjectLogin


    String getRole();

    void setRole(String role);

    boolean login();  // method retrieves oauth access_token from provider

    boolean logout(); // destroys the access_token from local cache

    void signup(SocialObject loginButton); // tries to retrieve user data from provider and saves it to database
}
