package hr.mars.muzicow.services;
import android.content.Context;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
/**
 * Created by mars on 27/01/16.
 */
public interface SocialAuth<LoginObject, ContextObject, SocialObject> {
    /***
     * SocialAuth interface for implementing social authentication from different providers in 
     * the MusicCow app. 
     * @param ctx
     */
    void setContext(ContextObject ctx);
    ContextObject getContext();

    void setSocialObject (SocialObject socialButton);
    SocialObject getsocialObject();

    void setProvider(String provider);
    String getProvider();

    void setKey(String key);
    String getKey();

    void setSecret(String key);
    String getSecret();

    void setup();
    boolean save(LoginObject object_login); // should serialize and save data to global cache, returns error code

    LoginObject load(); // reads from global cache and returns the ObjectLogin

    void setRole(String role);
    String getRole();
    boolean login();  // method retrieves oauth access_token from provider
    boolean logout(); // destroys the access_token from local cache
    void signup(SocialObject loginButton); // tries to retrieve user data from provider and saves it to database
}
