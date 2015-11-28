package hr.mars.muzicow.Interface;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Emil on 28.11.2015..
 */
public class Sesija {
    private static Sesija firstInstance = null;

    public static Sesija getInstance() {
        if (firstInstance == null) {
            firstInstance = new Sesija();
        }
        return firstInstance;
    }
    TwitterSession sesija;
    public void setSesija(TwitterSession sesija) {
        this.sesija = sesija;
    }

    public TwitterSession getSesija() {
        return sesija;
    }
}
