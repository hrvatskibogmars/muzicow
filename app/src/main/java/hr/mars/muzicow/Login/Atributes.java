package hr.mars.muzicow.Login;

import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Emil on 26.12.2015..
 */
public class Atributes {
    String role;
    TwitterSession session;

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
