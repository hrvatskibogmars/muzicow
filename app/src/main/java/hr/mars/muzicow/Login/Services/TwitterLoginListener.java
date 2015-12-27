package hr.mars.muzicow.Login.Services;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by Emil on 26.12.2015..
 */
public interface TwitterLoginListener {
    public void success(Result<User> userOb);
    public void failure(TwitterException e);
}
