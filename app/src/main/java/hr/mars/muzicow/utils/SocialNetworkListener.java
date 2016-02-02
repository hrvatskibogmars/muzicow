package hr.mars.muzicow.utils;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by Emil on 29.1.2016..
 */
public interface SocialNetworkListener {

        void success(Result<Boolean> b);
        void failure(TwitterException e);

}
