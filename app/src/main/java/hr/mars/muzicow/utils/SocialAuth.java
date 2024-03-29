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

    String getKey();

    void setKey(String key);

    String getSecret();

    void setSecret(String key);

    void setup();

    String getRole();

    void setRole(String role);

    void logout();

    void signup(SocialObject loginButton);
}
