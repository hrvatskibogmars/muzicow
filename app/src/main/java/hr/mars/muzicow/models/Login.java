package hr.mars.muzicow.models;

import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Emil on 26.12.2015..
 */
public class Login {
    String role;
    TwitterSession session;
    DJ djObject;


    public DJ getDjObject() {
        return djObject;
    }

    public void setDjObject(DJ djObject) {
        this.djObject = djObject;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public TwitterSession getSession() {
        return session;
    }

    public void setSession(TwitterSession session) {
        this.session = session;
    }

}
