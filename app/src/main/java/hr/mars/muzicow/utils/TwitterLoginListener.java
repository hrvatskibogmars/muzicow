package hr.mars.muzicow.utils;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by Emil on 26.12.2015..
 */
public interface TwitterLoginListener {
    void success(Result<User> userOb);
    void failure(TwitterException e);
}
