package hr.mars.muzicow.Login;

import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import static hr.mars.muzicow.Login.LoginActivity.at;

/**
 * Created by Emil on 25.12.2015..
 */
public class TwitterRetData implements SNetwork {

    private TwitterLoginListener listener;

    public void setListener( TwitterLoginListener listener){
        this.listener = listener;
    }


//public static ArrayList<DJ> djObjectL = new ArrayList<>();
  //  public static DJ djObject = new DJ();


    public void retSNetData(){

        Twitter.getApiClient(at.getSession()).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {
                        //  myIntent=new Intent(TwitterRetData.this, FragmentAdapter.class);
                        //SdjObject = new DJ();
                        ;
                        // djObjectL.get(0).set_ID(userResult.data.idStr);
                        /*
                        djObject.set_ID(userResult.data.idStr);
                        djObject.setName(userResult.data.name);
                        djObject.setDescription(userResult.data.description);
                        djObject.setCity(userResult.data.location);
                        djObject.setProfileUrl(userResult.data.profileImageUrl);
                        djObject.setNickname(userResult.data.screenName);
*/

                            listener.success(userResult);



                        // samo ovdje mogu uzet dj-a sa setom i getom il nnekak


                        Log.d("prikaz", userResult.data.idStr);
                        Log.d("prikaz", userResult.data.name);
                        Log.d("prikaz", userResult.data.description);
                        Log.d("prikaz", userResult.data.location);
                        Log.d("prikaz", userResult.data.profileImageUrl);


                    }

                    @Override
                    public void failure(TwitterException e) {
                        if (listener != null) {
                            listener.failure(e);
                        }
                        Log.d("prikaz", e.getMessage());
                    }
                });


            //Log.d("prikaz", "id od dj ret" + djObject.get_ID());


    }

}
