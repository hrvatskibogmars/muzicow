package hr.mars.muzicow.utils;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import hr.mars.muzicow.models.Login;

/**
 * Created by Emil on 25.12.2015..
 */
public class TwitterRetData {
    private TwitterLoginListener listener;

    public void setListener(TwitterLoginListener listener) {
        this.listener = listener;
    }

    public void retSNetData() {
        Twitter.getApiClient(((Login) Registry.getInstance().get("login.atr")).getSession()).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {
                        if (listener != null) {
                            listener.success(userResult);
                        }
                    }

                    @Override
                    public void failure(TwitterException e) {
                        if (listener != null) {
                            listener.failure(e);
                        }
                    }
                });
    }
}
